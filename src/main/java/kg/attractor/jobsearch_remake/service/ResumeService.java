package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResumeDao;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.model.Resume;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeDao resumeDao;

    public List<ResumeDto> getAllDto() {
        return resumeDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public List<ResumeDto> getByCategory(Integer categoryId) {
        return resumeDao.findByCategory(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public void create(ResumeDto dto) {
        Resume r = Resume.builder()
                .applicantId(1)
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .isActive(dto.isActive())
                .build();

        resumeDao.create(r);
    }

    public void update(Integer id, ResumeDto dto) {
        Resume r = Resume.builder()
                .id(id)
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .isActive(dto.isActive())
                .build();

        resumeDao.update(r);
    }

    public void delete(Integer id) {
        resumeDao.delete(id);
    }

    private ResumeDto toDto(Resume r) {
        return ResumeDto.builder()
                .id(r.getId())
                .name(r.getName())
                .categoryId(r.getCategoryId())
                .salary(r.getSalary())
                .isActive(r.isActive())
                .build();
    }
}