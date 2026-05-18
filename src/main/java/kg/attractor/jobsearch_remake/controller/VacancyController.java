package kg.attractor.jobsearch_remake.controller;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.service.ResponseService;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api/vacancies")
@RequiredArgsConstructor
public class VacancyController {

    private final VacancyService vacancyService;
    private final ResponseService responseService;
    private final UserService userService;
    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok(vacancyService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VacancyDto> getVacancyById(@PathVariable Long id) {
        return ResponseEntity.ok(vacancyService.getById(id));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<List<VacancyDto>> getByAuthor(@PathVariable Long authorId) {
        return ResponseEntity.ok(vacancyService.getByAuthor(authorId));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(vacancyService.getByCategory(id));
    }

    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        return ResponseEntity.ok(vacancyService.getActive());
    }

    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<UserDto>> getApplicantsByVacancy(@PathVariable Long id) {
        return ResponseEntity.ok(responseService.getUsersByVacancy(id));
    }

    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@Valid @RequestBody VacancyDto vacancyDto) {
        vacancyService.create(vacancyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Long id,
                                                    @Valid @RequestBody VacancyDto vacancyDto) {
        vacancyService.update(id, vacancyDto);
        vacancyDto.setId(id);
        return ResponseEntity.ok(vacancyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Long id) {
        vacancyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/respond")
    public ResponseEntity<Void> respondToVacancy(@PathVariable Long id,
                                                 @RequestParam Long resumeId,
                                                 Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        ResumeDto resume = resumeService.getById(resumeId);

        if (!resume.getApplicantId().equals(user.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        boolean responded = responseService.respond(resumeId, id);
        if (!responded) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }
}