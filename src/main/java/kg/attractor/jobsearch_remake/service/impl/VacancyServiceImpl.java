package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.exception.VacancyNotFoundException;
import kg.attractor.jobsearch_remake.model.Category;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.model.Vacancy;
import kg.attractor.jobsearch_remake.repository.CategoryRepository;
import kg.attractor.jobsearch_remake.repository.UserRepository;
import kg.attractor.jobsearch_remake.repository.VacancyRepository;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getAllDto() {
        return vacancyRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VacancyDto> getActivePaged(int page, int size, String sort) {
        if ("responses".equals(sort)) {
            return vacancyRepository.findActiveOrderByResponseCount(PageRequest.of(page, size))
                    .map(this::toDto);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate")));
        return vacancyRepository.findByActiveTrue(pageable).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getByCategory(Integer categoryId) {
        return vacancyRepository.findByCategory_Id(categoryId).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getActive() {
        return vacancyRepository.findByActiveTrue().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public VacancyDto getById(Long id) {
        return vacancyRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Вакансия не найдена с id: {}", id);
                    return new VacancyNotFoundException();
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<VacancyDto> getByAuthor(Long authorId) {
        User author = userRepository.findById(authorId).orElseThrow(UserNotFoundException::new);
        return vacancyRepository.findByAuthor(author).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VacancyDto> getByAuthorPaged(Long authorId, int page, int size) {
        User author = userRepository.findById(authorId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createdDate")));
        return vacancyRepository.findByAuthor(author, pageable).map(this::toDto);
    }

    @Override
    @Transactional
    public void create(VacancyDto dto) {
        log.info("Создание вакансии: {}", dto.getName());
        User author = userRepository.findById(dto.getAuthorId()).orElseThrow(UserNotFoundException::new);
        Category category = dto.getCategoryId() != null
                ? categoryRepository.findById(dto.getCategoryId()).orElse(null)
                : null;
        vacancyRepository.save(Vacancy.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(category)
                .salary(dto.getSalary() != null ? dto.getSalary() : 0.0)
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .active(dto.isActive())
                .author(author)
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build());
    }

    @Override
    @Transactional
    public void update(Long id, VacancyDto dto) {
        Vacancy v = vacancyRepository.findById(id).orElseThrow(VacancyNotFoundException::new);
        Category category = dto.getCategoryId() != null
                ? categoryRepository.findById(dto.getCategoryId()).orElse(null)
                : null;
        v.setName(dto.getName());
        v.setDescription(dto.getDescription());
        v.setCategory(category);
        v.setSalary(dto.getSalary() != null ? dto.getSalary() : 0.0);
        v.setExpFrom(dto.getExpFrom());
        v.setExpTo(dto.getExpTo());
        v.setActive(dto.isActive());
        v.setUpdateTime(LocalDateTime.now());
        vacancyRepository.save(v);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.warn("Удаление вакансии id: {}", id);
        vacancyRepository.deleteById(id);
    }

    private VacancyDto toDto(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategory() != null ? v.getCategory().getId() : null)
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .active(v.isActive())
                .authorId(v.getAuthor().getId())
                .createdDate(v.getCreatedDate())
                .updateTime(v.getUpdateTime())
                .build();
    }
}