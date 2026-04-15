package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dao.UserDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public List<UserDto> getAll() {
        log.info("Fetching all users");
        return userDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public UserDto findById(Integer id) {
        log.info("Fetching user by id: {}", id);
        return userDao.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new RuntimeException("User not found: " + id);
                });
    }

    @Override
    public List<UserDto> getEmployers() {
        log.info("Fetching all employers");
        return userDao.findByAccountType("EMPLOYER").stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public List<UserDto> getApplicants() {
        log.info("Fetching all applicants");
        return userDao.findByAccountType("APPLICANT").stream()
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
                .phoneNumber(dto.getPhoneNumber())
                .avatar(dto.getAvatar())
                .accountType(dto.getAccountType())
                .build();
        userDao.create(user);
    }

    @Override
    public void update(Integer id, UserDto dto) {
        log.info("Updating user id: {}", id);
        User user = User.builder()
                .id(id)
                .name(dto.getName())
                .surname(dto.getSurname())
                .age(dto.getAge())
                .phoneNumber(dto.getPhoneNumber())
                .avatar(dto.getAvatar())
                .build();
        userDao.update(user);
    }

    @Override
    public void delete(Integer id) {
        log.warn("Deleting user id: {}", id);
        userDao.delete(id);
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