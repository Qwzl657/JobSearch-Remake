package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.repository.UserRepository;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAll() {
        log.info("Fetching all users");
        return userRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto findById(Integer id) {
        log.info("Fetching user by id: {}", id);
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new NoSuchElementException("User not found: " + id);
                });
    }

    @Override
    public UserDto getByEmail(String email) {
        log.info("Fetching user by email: {}", email);
        return userRepository.findByEmail(email)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new NoSuchElementException("User not found: " + email);
                });
    }

    @Override
    public List<UserDto> getEmployers() {
        log.info("Fetching all employers");
        return userRepository.findByAccountType("EMPLOYER").stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getApplicants() {
        log.info("Fetching all applicants");
        return userRepository.findByAccountType("APPLICANT").stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public void create(UserDto dto) {
        log.info("Creating user: {}", dto.getEmail());
        User user = User.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phoneNumber(dto.getPhoneNumber())
                .avatar(dto.getAvatar() != null ? dto.getAvatar() : "default.png")
                .accountType(dto.getAccountType())
                .enabled(true)
                .build();
        userRepository.save(user);
    }

    @Override
    public void update(Integer id, UserDto dto) {
        log.info("Updating user id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found: " + id));
        user.setName(dto.getName());
        user.setSurname(dto.getSurname());
        user.setAge(dto.getAge());
        user.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getAvatar() != null) {
            user.setAvatar(dto.getAvatar());
        }
        userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        log.warn("Deleting user id: {}", id);
        userRepository.deleteById(id);
    }

    private UserDto toDto(User u) {
        return UserDto.builder()
                .id(u.getId().longValue())
                .name(u.getName())
                .surname(u.getSurname())
                .age(u.getAge())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .avatar(u.getAvatar())
                .accountType(u.getAccountType())
                .build();
    }
}