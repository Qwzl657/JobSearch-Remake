package kg.attractor.jobsearch_remake.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch_remake.common.UrlBuilder;
import kg.attractor.jobsearch_remake.dto.UserCreateDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.repository.UserRepository;
import kg.attractor.jobsearch_remake.service.EmailService;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<UserDto> getAll() {
        log.info("Получение всех пользователей");
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto findById(Long id) {
        log.info("Получение пользователя по id: {}", id);
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Пользователь не найден с id: {}", id);
                    return new UserNotFoundException();
                });
    }

    @Override
    public UserDto getByEmail(String email) {
        log.info("Получение пользователя по email: {}", email);
        return userRepository.findByEmail(email)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Пользователь не найден с email: {}", email);
                    return new UserNotFoundException();
                });
    }

    @Override
    public List<UserDto> getEmployers() {
        log.info("Получение всех работодателей");
        return userRepository.findByAccountType("EMPLOYER").stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getApplicants() {
        log.info("Получение всех соискателей");
        return userRepository.findByAccountType("APPLICANT").stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void create(UserCreateDto dto) {
        log.info("Создание пользователя: {}", dto.getEmail());
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .avatar("default.png")
                .accountType(dto.getAccountType())
                .enabled(true)
                .build();
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(Long id, UserDto dto) {
        log.info("Обновление пользователя id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Пользователь не найден с id: {}", id);
                    return new UserNotFoundException();
                });

        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());

        if (dto.getAvatar() != null && !dto.getAvatar().isBlank()) {
            user.setAvatar(dto.getAvatar());
        }

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.warn("Удаление пользователя id: {}", id);
        userRepository.deleteById(id);
    }

    private void updateResetPasswordToken(String token, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);
        user.setResetPasswordToken(token);
        userRepository.saveAndFlush(user);
    }

    @Override
    public boolean isResetTokenValid(String token) {
        return userRepository.findByResetPasswordToken(token).isPresent();
    }

    @Override
    public void makeResetPwdLink(HttpServletRequest request)
            throws UserNotFoundException, MessagingException, UnsupportedEncodingException {
        String email = request.getParameter("email");
        String token = UUID.randomUUID().toString();
        updateResetPasswordToken(token, email);

        String resetLink = UrlBuilder.getSiteUrl(request)
                + "/auth/reset-password?token=" + token;
        Locale locale = LocaleContextHolder.getLocale();
        emailService.send(email, resetLink, locale);
    }

    @Override
    public void autoLogin(String email, HttpServletRequest request, HttpServletResponse response) {
        log.info("Автоматический вход для: {}", email);
        UserDetails userDetails = loadUserByUsername(email);
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        HttpSessionSecurityContextRepository repo =
                new HttpSessionSecurityContextRepository();
        repo.saveContext(SecurityContextHolder.getContext(), request, response);
    }

    private UserDto toDto(User u) {
        return UserDto.builder()
                .id(u.getId())
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .avatar(u.getAvatar())
                .accountType(u.getAccountType())
                .build();
    }
    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> {
                    log.error("Токен сброса не найден: {}", token);
                    return new UserNotFoundException();
                });
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.saveAndFlush(user);
        log.info("Пароль успешно сброшен по токену");
    }
}