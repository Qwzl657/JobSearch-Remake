package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.dto.UserDto; // Для списка откликнувшихся
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {


    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Integer id, @RequestBody VacancyDto vacancyDto) {
        vacancyDto.setId(id);
        return ResponseEntity.ok(vacancyDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }


    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        // Здесь будет логика фильтрации isActive = true
        return ResponseEntity.ok().build();
    }


    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<UserDto>> getApplicantsByVacancy(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/respond")
    public ResponseEntity<Void> respondToVacancy(@PathVariable Integer id) {
        // Логика создания отклика
        return ResponseEntity.ok().build();
    }
}