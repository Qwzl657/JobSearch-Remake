package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.model.WorkExperienceInfo;
import kg.attractor.jobsearch_remake.repository.ResumeRepository;
import kg.attractor.jobsearch_remake.repository.WorkExperienceInfoRepository;
import kg.attractor.jobsearch_remake.service.WorkExperienceInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WorkExperienceInfoServiceImpl implements WorkExperienceInfoService {

    private final WorkExperienceInfoRepository workExperienceInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<WorkExperienceInfoDto> getByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        return workExperienceInfoRepository.findByResume(resume).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public void createForResume(Long resumeId, List<WorkExperienceInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        dtos.forEach(dto -> workExperienceInfoRepository.save(WorkExperienceInfo.builder()
                .resume(resume)
                .years(dto.getYears())
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .responsibilities(dto.getResponsibilities())
                .build()));
    }

    @Override
    @Transactional
    public void updateForResume(Long resumeId, List<WorkExperienceInfoDto> dtos) {
        if (dtos == null) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        workExperienceInfoRepository.deleteByResume(resume);
        createForResume(resumeId, dtos);
    }

    @Override
    @Transactional
    public void deleteByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        workExperienceInfoRepository.deleteByResume(resume);
    }

    private WorkExperienceInfoDto toDto(WorkExperienceInfo info) {
        return WorkExperienceInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResume().getId())
                .years(info.getYears())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .build();
    }
}