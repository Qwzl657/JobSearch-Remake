package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dao.UserDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDto getSampleUser() {
        return UserDto.builder()
                .username("test")
                .email("test@mail.com")
                .phoneNumber("123")
                .build();
    }

    @Override
    public void create(UserDto dto) {
        User user = User.builder()
                .name(dto.getUsername())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .build();

        userDao.create(user);
    }

    @Override
    public UserDto findById(Long id) {
        return userDao.findAll().stream()
                .filter(u -> u.getId().longValue() == id)
                .findFirst()
                .map(u -> UserDto.builder()
                        .id(u.getId().longValue())
                        .username(u.getName())
                        .email(u.getEmail())
                        .phoneNumber(u.getPhoneNumber())
                        .build())
                .orElse(null);
    }
}