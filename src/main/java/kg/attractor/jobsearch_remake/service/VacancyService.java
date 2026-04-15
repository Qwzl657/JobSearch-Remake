package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResponseDao;
import kg.attractor.jobsearch_remake.dao.VacancyDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyDao vacancyDao;

    public List<VacancyDto> getAllDto() {
        log.info("Fetching all vacancies");
        return vacancyDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public List<VacancyDto> getByCategory(Integer categoryId) {
        log.info("Fetching vacancies by category id: {}", categoryId);
        return vacancyDao.findByCategory(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<VacancyDto> getActive() {
        log.info("Fetching active vacancies");
        return vacancyDao.getActive().stream()
                .map(this::toDto)
                .toList();
    }

    public VacancyDto getById(Integer id) {
        log.info("Fetching vacancy by id: {}", id);
        return vacancyDao.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Vacancy not found with id: {}", id);
                    return new RuntimeException("Vacancy not found: " + id);
                });
    }

    public List<VacancyDto> getByAuthor(Integer authorId) {
        log.info("Fetching vacancies for author id: {}", authorId);
        return vacancyDao.findByAuthorId(authorId).stream()
                .map(this::toDto)
                .toList();
    }

    public void create(VacancyDto dto) {
        log.info("Creating vacancy: {}", dto.getName());
        Vacancy v = Vacancy.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.isActive())
                .authorId(dto.getAuthorId())
                .build();
        vacancyDao.create(v);
    }

    public void update(Integer id, VacancyDto dto) {
        log.info("Updating vacancy id: {}", id);
        Vacancy v = Vacancy.builder()
                .id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.isActive())
                .build();
        vacancyDao.update(v);
    }

    public void delete(Integer id) {
        log.warn("Deleting vacancy id: {}", id);
        vacancyDao.delete(id);
    }

    private VacancyDto toDto(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategoryId())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.isActive())
                .authorId(v.getAuthorId())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .build();
    }
}