package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResumeDao;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.model.Resume;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final ResumeDao resumeDao;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;

    public List<ResumeDto> getAllDto() {
        log.info("Fetching all resumes");
        return resumeDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public List<ResumeDto> getByCategory(Integer categoryId) {
        log.info("Fetching resumes by category id: {}", categoryId);
        return resumeDao.findByCategory(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<ResumeDto> getByApplicant(Integer applicantId) {
        log.info("Fetching resumes for applicant id: {}", applicantId);
        return resumeDao.findByApplicantId(applicantId).stream()
                .map(this::toDto)
                .toList();
    }

    public ResumeDto getById(Integer id) {
        log.info("Fetching resume by id: {}", id);
        return resumeDao.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Resume not found with id: {}", id);
                    return new RuntimeException("Resume not found: " + id);
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
                .build();


        Integer resumeId = resumeDao.createAndReturnId(r);


        workExperienceInfoService.createForResume(resumeId, dto.getWorkExperienceInfos());
        educationInfoService.createForResume(resumeId, dto.getEducationInfos());
        contactInfoService.createForResume(resumeId, dto.getContactInfos());
    }

    public void update(Integer id, ResumeDto dto) {
        log.info("Updating resume id: {}", id);
        Resume r = Resume.builder()
                .id(id)
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .isActive(dto.isActive())
                .build();
        resumeDao.update(r);


        workExperienceInfoService.updateForResume(id, dto.getWorkExperienceInfos());
        educationInfoService.updateForResume(id, dto.getEducationInfos());
        contactInfoService.updateForResume(id, dto.getContactInfos());
    }

    public void delete(Integer id) {
        log.warn("Deleting resume id: {}", id);

        workExperienceInfoService.deleteByResumeId(id);
        educationInfoService.deleteByResumeId(id);
        contactInfoService.deleteByResumeId(id);
        resumeDao.delete(id);
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