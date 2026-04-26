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

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyRepository vacancyRepository;

    public List<VacancyDto> getAllDto() {
        log.info("Fetching all vacancies");
        return vacancyRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Page<VacancyDto> getActivePaged(int page, int size, String sort) {
        log.info("Fetching active vacancies page: {}", page);
        Sort sortBy = sort.equals("responses")
                ? Sort.by(Sort.Order.desc("updateTime"))
                : Sort.by(Sort.Order.desc("updateTime"));
        Pageable pageable = PageRequest.of(page, size, sortBy);
        return vacancyRepository.findByIsActiveTrue(pageable).map(this::toDto);
    }

    public List<VacancyDto> getByCategory(Integer categoryId) {
        log.info("Fetching vacancies by category id: {}", categoryId);
        return vacancyRepository.findByCategoryId(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<VacancyDto> getActive() {
        log.info("Fetching active vacancies");
        return vacancyRepository.findByIsActiveTrue().stream()
                .map(this::toDto)
                .toList();
    }

    public VacancyDto getById(Integer id) {
        log.info("Fetching vacancy by id: {}", id);
        return vacancyRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Vacancy not found with id: {}", id);
                    return new NoSuchElementException("Vacancy not found: " + id);
                });
    }

    public List<VacancyDto> getByAuthor(Integer authorId) {
        log.info("Fetching vacancies for author id: {}", authorId);
        return vacancyRepository.findByAuthorId(authorId).stream()
                .map(this::toDto)
                .toList();
    }

    public Page<VacancyDto> getByAuthorPaged(Integer authorId, int page, int size) {
        log.info("Fetching vacancies for author id: {} page: {}", authorId, page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("updateTime")));
        return vacancyRepository.findByAuthorId(authorId, pageable).map(this::toDto);
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
                .createdDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
        vacancyRepository.save(v);
    }

    public void update(Integer id, VacancyDto dto) {
        log.info("Updating vacancy id: {}", id);
        Vacancy v = vacancyRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Vacancy not found: " + id));
        v.setName(dto.getName());
        v.setDescription(dto.getDescription());
        v.setCategoryId(dto.getCategoryId());
        v.setSalary(dto.getSalary());
        v.setExpFrom(dto.getExpFrom());
        v.setExpTo(dto.getExpTo());
        v.setActive(dto.isActive());
        v.setUpdateTime(LocalDate.now());
        vacancyRepository.save(v);
    }

    public void delete(Integer id) {
        log.warn("Deleting vacancy id: {}", id);
        vacancyRepository.deleteById(id);
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