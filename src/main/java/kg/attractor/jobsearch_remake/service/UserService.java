package kg.attractor.jobsearch_remake.service;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch_remake.dto.UserCreateDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.model.User;

import java.io.UnsupportedEncodingException;
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


    void makeResetPwdLink(HttpServletRequest request)
            throws UserNotFoundException, MessagingException, UnsupportedEncodingException;

    User getByResetPasswordToken(String token);

    void updatePassword(User user, String newPassword);
}