package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {


    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }


    @GetMapping("/employers/{id}")
    public ResponseEntity<UserDto> findEmployer(@PathVariable Long id) {

        return ResponseEntity.ok(new UserDto()); // Возвращаем объект, а не строку
    }


    @GetMapping("/applicants/{id}")
    public ResponseEntity<UserDto> findApplicant(@PathVariable Long id) {
        return ResponseEntity.ok(new UserDto());
    }
}