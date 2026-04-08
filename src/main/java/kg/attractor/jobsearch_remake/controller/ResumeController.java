package kg.attractor.jobsearch_remake.controller;

import kg.attractor.jobsearch_remake.dto.ResumeDto; // Твой новый DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resumes")
public class ResumeController {

    // 1. Создание резюме
    // Добавили @RequestBody, чтобы принимать JSON
    // Возвращаем объект ResumeDto и статус 201 Created
    @PostMapping
    public ResponseEntity<ResumeDto> createResume(@RequestBody ResumeDto resumeDto) {
        // Логика сохранения через сервис будет тут
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeDto);
    }

    // 2. Обновление резюме
    // Используем PathVariable для ID и Body для новых данных
    @PutMapping("/{id}")
    public ResponseEntity<ResumeDto> updateResume(@PathVariable Integer id, @RequestBody ResumeDto resumeDto) {
        // Устанавливаем ID из пути в объект (для надежности)
        resumeDto.setId(id);
        return ResponseEntity.ok(resumeDto);
    }

    // 3. Удаление резюме
    // По стандарту REST при удалении лучше возвращать 204 No Content
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResume(@PathVariable Integer id) {
        // Логика удаления...
        return ResponseEntity.noContent().build();
    }

    // 4. Получение всех резюме
    // Возвращаем список объектов List<ResumeDto>
    @GetMapping
    public ResponseEntity<List<ResumeDto>> getAllResumes() {
        // В будущем: return ResponseEntity.ok(resumeService.findAll());
        return ResponseEntity.ok().build();
    }

    // 5. Поиск по категории
    // Исправлено: возвращает список резюме конкретной категории
    @GetMapping("/category/{id}")
    public ResponseEntity<List<ResumeDto>> getResumesByCategory(@PathVariable Integer id) {
        // Логика поиска по ID категории
        return ResponseEntity.ok().build();
    }
}