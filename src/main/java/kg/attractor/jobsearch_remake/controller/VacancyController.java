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

    // 1. Создание вакансии
    @PostMapping
    public ResponseEntity<VacancyDto> createVacancy(@RequestBody VacancyDto vacancyDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacancyDto);
    }

    // 2. Обновление вакансии
    @PutMapping("/{id}")
    public ResponseEntity<VacancyDto> updateVacancy(@PathVariable Integer id, @RequestBody VacancyDto vacancyDto) {
        vacancyDto.setId(id);
        return ResponseEntity.ok(vacancyDto);
    }

    // 3. Удаление вакансии
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVacancy(@PathVariable Integer id) {
        return ResponseEntity.noContent().build();
    }

    // 4. Получение всех вакансий
    @GetMapping
    public ResponseEntity<List<VacancyDto>> getAllVacancies() {
        return ResponseEntity.ok().build();
    }

    // 5. Поиск вакансий по категории
    @GetMapping("/category/{id}")
    public ResponseEntity<List<VacancyDto>> getVacanciesByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    // --- НОВЫЕ МЕТОДЫ ПО ЗАМЕЧАНИЯМ РЕВЬЮЕРА ---

    // 6. Поиск всех АКТИВНЫХ вакансий (Замечание №2)
    @GetMapping("/active")
    public ResponseEntity<List<VacancyDto>> getActiveVacancies() {
        // Здесь будет логика фильтрации isActive = true
        return ResponseEntity.ok().build();
    }

    // 7. Поиск откликнувшихся соискателей на вакансию (Замечание №1)
    // Возвращаем список UserDto (соискателей)
    @GetMapping("/{id}/applicants")
    public ResponseEntity<List<UserDto>> getApplicantsByVacancy(@PathVariable Integer id) {
        return ResponseEntity.ok().build();
    }

    // 8. Отклик на вакансию (твой существующий метод)
    @PostMapping("/{id}/respond")
    public ResponseEntity<Void> respondToVacancy(@PathVariable Integer id) {
        // Логика создания отклика
        return ResponseEntity.ok().build();
    }
}