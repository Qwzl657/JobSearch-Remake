package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto getSampleUser(){
        UserDto user = new UserDto();
        user.setUsername("Alex");
        user.setEmail("qwe@qwe.qwe");
        user.setPassword("qwerty");
        return user;
    }

}
