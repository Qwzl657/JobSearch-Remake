package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.VacancyDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VacancyService {
    List<VacancyDto> getAllDto();
    Page<VacancyDto> getActivePaged(int page, int size, String sort);
    List<VacancyDto> getByCategory(Integer categoryId);
    List<VacancyDto> getActive();
    VacancyDto getById(Long id);
    List<VacancyDto> getByAuthor(Long authorId);
    Page<VacancyDto> getByAuthorPaged(Long authorId, int page, int size);
    void create(VacancyDto dto);
    void update(Long id, VacancyDto dto);
    void delete(Long id);
}