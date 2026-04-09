package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.UserDto;

public interface UserService {

    UserDto getSampleUser();

    void create(UserDto dto);

    UserDto findById(Long id);
}