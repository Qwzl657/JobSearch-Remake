package kg.attractor.jobsearch_remake.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeApiController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAll() {
        return ResponseEntity.ok(resumeService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(resumeService.getById(id));
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getByApplicant(@PathVariable Long applicantId) {
        return ResponseEntity.ok(resumeService.getByApplicant(applicantId));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ResumeDto>> getByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(resumeService.getByCategory(id));
    }
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody ResumeDto resumeDto,
                                       Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        resumeDto.setApplicantId(user.getId());
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody ResumeDto resumeDto,
                                       Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        ResumeDto existing = resumeService.getById(id);
        if (!existing.getApplicantId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        resumeService.update(id, resumeDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id,
                                       Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        ResumeDto existing = resumeService.getById(id);
        if (!existing.getApplicantId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        resumeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}