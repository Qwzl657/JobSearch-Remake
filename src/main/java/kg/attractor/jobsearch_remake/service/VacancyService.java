package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.model.Vacancy;
import kg.attractor.jobsearch_remake.repository.VacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    @Transactional(readOnly = true)
    public List<VacancyDto> getAllDto() {
        log.info("Получение всех вакансий");
        return vacancyRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<VacancyDto> getActivePaged(int page, int size, String sort) {
        log.info("Получение активных вакансий, страница: {}, сортировка: {}", page, sort);

        if ("responses".equals(sort)) {
            Pageable pageable = PageRequest.of(page, size);
            return vacancyRepository.findActiveOrderByResponseCount(pageable)
                    .map(this::toDto);
        }

        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("createdDate")));
        return vacancyRepository.findByIsActiveTrue(pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<VacancyDto> getByCategory(Integer categoryId) {
        log.info("Получение вакансий по категории id: {}", categoryId);
        return vacancyRepository.findByCategoryId(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<VacancyDto> getActive() {
        log.info("Получение активных вакансий");
        return vacancyRepository.findByIsActiveTrue().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public VacancyDto getById(Integer id) {
        log.info("Получение вакансии по id: {}", id);
        return vacancyRepository.findById(id.longValue())
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Вакансия не найдена с id: {}", id);
                    return new NoSuchElementException("Вакансия не найдена: " + id);
                });
    }

    @Transactional(readOnly = true)
    public List<VacancyDto> getByAuthor(Integer authorId) {
        log.info("Получение вакансий автора id: {}", authorId);
        return vacancyRepository.findByAuthorId(authorId.longValue()).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<VacancyDto> getByAuthorPaged(Integer authorId, int page, int size) {
        log.info("Получение вакансий автора id: {}, страница: {}", authorId, page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("createdDate")));
        return vacancyRepository.findByAuthorId(authorId.longValue(), pageable)
                .map(this::toDto);
    }

    @Transactional
    public void create(VacancyDto dto) {
        log.info("Создание вакансии: {}", dto.getName());
        Vacancy v = Vacancy.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.isActive())
                .authorId(dto.getAuthorId().longValue())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        vacancyRepository.save(v);
    }

    @Transactional
    public void update(Integer id, VacancyDto dto) {
        log.info("Обновление вакансии id: {}", id);
        Vacancy v = vacancyRepository.findById(id.longValue())
                .orElseThrow(() -> new NoSuchElementException("Вакансия не найдена: " + id));
        v.setName(dto.getName());
        v.setDescription(dto.getDescription());
        v.setCategoryId(dto.getCategoryId());
        v.setSalary(dto.getSalary());
        v.setExpFrom(dto.getExpFrom());
        v.setExpTo(dto.getExpTo());
        v.setActive(dto.isActive());
        v.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(v);
    }

    @Transactional
    public void delete(Integer id) {
        log.warn("Удаление вакансии id: {}", id);
        vacancyRepository.deleteById(id.longValue());
    }

    private VacancyDto toDto(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId().intValue())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategoryId())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.isActive())
                .authorId(v.getAuthorId().intValue())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .build();
    }
}