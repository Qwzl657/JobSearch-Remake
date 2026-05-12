package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.*;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.model.*;
import kg.attractor.jobsearch_remake.repository.*;
import kg.attractor.jobsearch_remake.service.*;
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
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final WorkExperienceInfoService workExperienceInfoService;
    private final EducationInfoService educationInfoService;
    private final ContactInfoService contactInfoService;

    @Override
    @Transactional(readOnly = true)
    public List<ResumeDto> getAllDto() {
        return resumeRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResumeDto> getAllPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findAll(pageable).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResumeDto> getByCategory(Integer categoryId) {
        return resumeRepository.findByCategory_Id(categoryId).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResumeDto> getByApplicant(Long applicantId) {
        User user = userRepository.findById(applicantId).orElseThrow(UserNotFoundException::new);
        return resumeRepository.findByApplicant(user).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResumeDto> getByApplicantPaged(Long applicantId, int page, int size) {
        User user = userRepository.findById(applicantId).orElseThrow(UserNotFoundException::new);
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findByApplicant(user, pageable).map(this::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public ResumeDto getById(Long id) {
        return resumeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Резюме не найдено с id: {}", id);
                    return new ResumeNotFoundException();
                });
    }

    @Override
    @Transactional
    public void create(ResumeDto dto) {
        log.info("Создание резюме: {}", dto.getName());
        User applicant = userRepository.findById(dto.getApplicantId())
                .orElseThrow(UserNotFoundException::new);
        Category category = dto.getCategoryId() != null
                ? categoryRepository.findById(dto.getCategoryId()).orElse(null)
                : null;
        Resume r = Resume.builder()
                .applicant(applicant)
                .name(dto.getName())
                .category(category)
                .salary(dto.getSalary() != null ? dto.getSalary() : 0.0)
                .isActive(dto.isActive())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        Resume saved = resumeRepository.save(r);
        workExperienceInfoService.createForResume(saved.getId(), dto.getWorkExperienceInfos());
        educationInfoService.createForResume(saved.getId(), dto.getEducationInfos());
        contactInfoService.createForResume(saved.getId(), dto.getContactInfos());
    }

    @Override
    @Transactional
    public void update(Long id, ResumeDto dto) {
        log.info("Обновление резюме id: {}", id);
        Resume r = resumeRepository.findById(id).orElseThrow(ResumeNotFoundException::new);
        Category category = dto.getCategoryId() != null
                ? categoryRepository.findById(dto.getCategoryId()).orElse(null)
                : null;
        r.setName(dto.getName());
        r.setCategory(category);
        r.setSalary(dto.getSalary() != null ? dto.getSalary() : 0.0);
        r.setActive(dto.isActive());
        r.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(r);
        workExperienceInfoService.updateForResume(id, dto.getWorkExperienceInfos());
        educationInfoService.updateForResume(id, dto.getEducationInfos());
        contactInfoService.updateForResume(id, dto.getContactInfos());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.warn("Удаление резюме id: {}", id);
        workExperienceInfoService.deleteByResumeId(id);
        educationInfoService.deleteByResumeId(id);
        contactInfoService.deleteByResumeId(id);
        resumeRepository.deleteById(id);
    }

    private ResumeDto toDto(Resume r) {
        return ResumeDto.builder()
                .id(r.getId())
                .applicantId(r.getApplicant().getId())
                .name(r.getName())
                .categoryId(r.getCategory() != null ? r.getCategory().getId() : null)
                .salary(r.getSalary())
                .active(r.isActive())
                .createdDate(r.getCreatedDate())
                .updateTime(r.getUpdateTime())
                .workExperienceInfos(workExperienceInfoService.getByResumeId(r.getId()))
                .educationInfos(educationInfoService.getByResumeId(r.getId()))
                .contactInfos(contactInfoService.getByResumeId(r.getId()))
                .build();
    }
}