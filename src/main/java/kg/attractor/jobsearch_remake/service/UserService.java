package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAll();
    UserDto findById(Integer id);
    List<UserDto> getEmployers();
    List<UserDto> getApplicants();
    void create(UserDto dto);
    void update(Integer id, UserDto dto);
    void delete(Integer id);
}