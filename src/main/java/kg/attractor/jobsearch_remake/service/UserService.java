package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.UserCreateDto;
import kg.attractor.jobsearch_remake.dto.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAll();

    UserDto findById(Long id);

    UserDto getByEmail(String email);

    List<UserDto> getEmployers();

    List<UserDto> getApplicants();

    void create(UserCreateDto dto);

    void update(Long id, UserDto dto);

    void delete(Long id);
}