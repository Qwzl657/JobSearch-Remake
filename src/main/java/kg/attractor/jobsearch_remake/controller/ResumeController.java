package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        return ResponseEntity.ok(resumeService.getAllDto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResumeDto> getResumeById(@PathVariable Integer id) {
        return ResponseEntity.ok(resumeService.getById(id));
    }

    @GetMapping("/applicant/{applicantId}")
    public ResponseEntity<List<ResumeDto>> getByApplicant(@PathVariable Integer applicantId) {
        return ResponseEntity.ok(resumeService.getByApplicant(applicantId));
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(resumeService.getByCategory(id));
    }

    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {
        resumeService.create(resumeDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Integer id,
                                                  @RequestBody ResumeDto resumeDto) {
        resumeService.update(id, resumeDto);
        resumeDto.setId(id);
        return ResponseEntity.ok(resumeDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        resumeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}