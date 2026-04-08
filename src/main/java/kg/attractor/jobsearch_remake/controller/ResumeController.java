package kg.attractor.jobsearch_remake.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    @PostMapping
    public ResponseEntity<String> createResume() {
        return ResponseEntity.ok("Resume created");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateResume(@PathVariable Long id) {
        return ResponseEntity.ok("Resume updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResume(@PathVariable Long id) {
        return ResponseEntity.ok("Resume deleted");
    }

    @GetMapping
    public ResponseEntity<String> getAllResumes() {
        return ResponseEntity.ok("All resumes");
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<String> getResumesByCategory(@PathVariable Long id) {
        return ResponseEntity.ok("Resumes by category");
    }

}