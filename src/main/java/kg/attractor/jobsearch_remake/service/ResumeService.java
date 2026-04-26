package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.repository.ResumeRepository;
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
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;

    public List<ResumeDto> getAllDto() {
        log.info("Fetching all resumes");
        return resumeRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public Page<ResumeDto> getAllPaged(int page, int size) {
        log.info("Fetching resumes page: {}", page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findAll(pageable).map(this::toDto);
    }

    public List<ResumeDto> getByCategory(Integer categoryId) {
        log.info("Fetching resumes by category id: {}", categoryId);
        return resumeRepository.findByCategoryId(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<ResumeDto> getByApplicant(Integer applicantId) {
        log.info("Fetching resumes for applicant id: {}", applicantId);
        return resumeRepository.findByApplicantId(applicantId).stream()
                .map(this::toDto)
                .toList();
    }

    public Page<ResumeDto> getByApplicantPaged(Integer applicantId, int page, int size) {
        log.info("Fetching resumes for applicant id: {} page: {}", applicantId, page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findByApplicantId(applicantId, pageable).map(this::toDto);
    }

    public ResumeDto getById(Integer id) {
        log.info("Fetching resume by id: {}", id);
        return resumeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Resume not found with id: {}", id);
                    return new NoSuchElementException("Resume not found: " + id);
                });
    }

    public void create(ResumeDto dto) {
        log.info("Creating resume: {}", dto.getName());
        Resume r = Resume.builder()
                .applicantId(dto.getApplicantId())
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .isActive(dto.isActive())
                .createdDate(LocalDate.now())
                .updateTime(LocalDate.now())
                .build();
        Resume saved = resumeRepository.save(r);
        workExperienceInfoService.createForResume(saved.getId(), dto.getWorkExperienceInfos());
        educationInfoService.createForResume(saved.getId(), dto.getEducationInfos());
        contactInfoService.createForResume(saved.getId(), dto.getContactInfos());
    }

    public void update(Integer id, ResumeDto dto) {
        log.info("Updating resume id: {}", id);
        Resume r = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Resume not found: " + id));
        r.setName(dto.getName());
        r.setCategoryId(dto.getCategoryId());
        r.setSalary(dto.getSalary());
        r.setActive(dto.isActive());
        r.setUpdateTime(LocalDate.now());
        resumeRepository.save(r);
        workExperienceInfoService.updateForResume(id, dto.getWorkExperienceInfos());
        educationInfoService.updateForResume(id, dto.getEducationInfos());
        contactInfoService.updateForResume(id, dto.getContactInfos());
    }

    public void delete(Integer id) {
        log.warn("Deleting resume id: {}", id);
        workExperienceInfoService.deleteByResumeId(id);
        educationInfoService.deleteByResumeId(id);
        contactInfoService.deleteByResumeId(id);
        resumeRepository.deleteById(id);
    }

    private ResumeDto toDto(Resume r) {
        return ResumeDto.builder()
                .id(r.getId())
                .applicantId(r.getApplicantId())
                .name(r.getName())
                .categoryId(r.getCategoryId())
                .salary(r.getSalary())
                .isActive(r.isActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .workExperienceInfos(workExperienceInfoService.getByResumeId(r.getId()))
                .educationInfos(educationInfoService.getByResumeId(r.getId()))
                .contactInfos(contactInfoService.getByResumeId(r.getId()))
                .build();
    }
}