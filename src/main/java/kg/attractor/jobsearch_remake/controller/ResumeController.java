package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.ResumeDto; // Твой новый DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {


    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Integer id, @RequestBody ResumeDto resumeDto) {

        resumeDto.setId(id);
        return ResponseEntity.ok(resumeDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {

        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        // В будущем: return ResponseEntity.ok(resumeService.findAll());
        return ResponseEntity.ok().build();
    }


    @GetMapping("/category/{id}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable Integer id) {
        // Логика поиска по ID категории
        return ResponseEntity.ok().build();
    }
}