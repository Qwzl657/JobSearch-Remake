package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dao.UserDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public UserDto getSampleUser() {
        return UserDto.builder()
                .username("test")
                .email("test@mail.com")
                .password("123")
                .build();
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}