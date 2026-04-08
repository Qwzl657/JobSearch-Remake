package kg.attractor.jobsearch_remake.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacancies")
public class VacancyController {

    @PostMapping
    public ResponseEntity<String> createVacancy() {
        return ResponseEntity.ok("Vacancy created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateVacancy(@PathVariable Long id) {
        return ResponseEntity.ok("Vacancy updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVacancy(@PathVariable Long id) {
        return ResponseEntity.ok("Vacancy deleted");
    }

    @GetMapping
    public ResponseEntity<String> getAllVacancies() {
        return ResponseEntity.ok("All vacancies");
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<String> getVacanciesByCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Vacancies by category");
    }

    @PostMapping("/{id}/respond")
    public ResponseEntity<String> respondVacancy(@PathVariable Long id) {
        return ResponseEntity.ok("Responded to vacancy");
    }

}