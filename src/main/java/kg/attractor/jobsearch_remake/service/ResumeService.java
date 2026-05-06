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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Transactional(readOnly = true)
    public List<ResumeDto> getAllDto() {
        log.info("Получение всех резюме");
        return resumeRepository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<ResumeDto> getAllPaged(int page, int size) {
        log.info("Получение резюме, страница: {}", page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findAll(pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public List<ResumeDto> getByCategory(Integer categoryId) {
        log.info("Получение резюме по категории id: {}", categoryId);
        return resumeRepository.findByCategoryId(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<ResumeDto> getByApplicant(Integer applicantId) {
        log.info("Получение резюме соискателя id: {}", applicantId);
        return resumeRepository.findByApplicantId(applicantId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public Page<ResumeDto> getByApplicantPaged(Integer applicantId, int page, int size) {
        log.info("Получение резюме соискателя id: {}, страница: {}", applicantId, page);
        Pageable pageable = PageRequest.of(page, size,
                Sort.by(Sort.Order.desc("updateTime")));
        return resumeRepository.findByApplicantId(applicantId, pageable).map(this::toDto);
    }

    @Transactional(readOnly = true)
    public ResumeDto getById(Integer id) {
        log.info("Получение резюме по id: {}", id);
        return resumeRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> {
                    log.error("Резюме не найдено с id: {}", id);
                    return new NoSuchElementException("Резюме не найдено: " + id);
                });
    }

    @Transactional
    public void create(ResumeDto dto) {
        log.info("Создание резюме: {}", dto.getName());
        Resume r = Resume.builder()
                .applicantId(dto.getApplicantId())
                .name(dto.getName())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .isActive(dto.isActive())
                .createdDate(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .build();
        Resume saved = resumeRepository.save(r);
        workExperienceInfoService.createForResume(saved.getId(), dto.getWorkExperienceInfos());
        educationInfoService.createForResume(saved.getId(), dto.getEducationInfos());
        contactInfoService.createForResume(saved.getId(), dto.getContactInfos());
    }

    @Transactional
    public void update(Integer id, ResumeDto dto) {
        log.info("Обновление резюме id: {}", id);
        Resume r = resumeRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Резюме не найдено: " + id));
        r.setName(dto.getName());
        r.setCategoryId(dto.getCategoryId());
        r.setSalary(dto.getSalary());
        r.setActive(dto.isActive());
        r.setUpdateTime(LocalDateTime.now());
        resumeRepository.save(r);
        workExperienceInfoService.updateForResume(id, dto.getWorkExperienceInfos());
        educationInfoService.updateForResume(id, dto.getEducationInfos());
        contactInfoService.updateForResume(id, dto.getContactInfos());
    }

    @Transactional
    public void delete(Integer id) {
        log.warn("Удаление резюме id: {}", id);
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