package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ResumeService {
    List<ResumeDto> getAllDto();
    Page<ResumeDto> getAllPaged(int page, int size);
    List<ResumeDto> getByCategory(Integer categoryId);
    List<ResumeDto> getByApplicant(Long applicantId);
    Page<ResumeDto> getByApplicantPaged(Long applicantId, int page, int size);
    ResumeDto getById(Long id);
    void create(ResumeDto dto);
    void update(Long id, ResumeDto dto);
    void delete(Long id);
}