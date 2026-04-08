package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    // создание пользователя
    @PostMapping
    public ResponseEntity<String> createUser() {
        return ResponseEntity.ok("User created");
    }

    // поиск работодателей
    @GetMapping("/employers")
    public ResponseEntity<String> findEmployers() {
        return ResponseEntity.ok("Employers list");
    }

    // поиск соискателей
    @GetMapping("/applicants")
    public ResponseEntity<String> findApplicants() {
        return ResponseEntity.ok("Applicants list");
    }
}