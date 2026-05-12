package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.EducationInfoDto;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.model.EducationInfo;
import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.repository.EducationInfoRepository;
import kg.attractor.jobsearch_remake.repository.ResumeRepository;
import kg.attractor.jobsearch_remake.service.EducationInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationInfoServiceImpl implements EducationInfoService {

    private final EducationInfoRepository educationInfoRepository;
    private final ResumeRepository resumeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<EducationInfoDto> getByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        return educationInfoRepository.findByResume(resume).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public void createForResume(Long resumeId, List<EducationInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        dtos.forEach(dto -> educationInfoRepository.save(EducationInfo.builder()
                .resume(resume)
                .institution(dto.getInstitution())
                .program(dto.getProgram())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .degree(dto.getDegree())
                .build()));
    }

    @Override
    @Transactional
    public void updateForResume(Long resumeId, List<EducationInfoDto> dtos) {
        if (dtos == null) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        educationInfoRepository.deleteByResume(resume);
        createForResume(resumeId, dtos);
    }

    @Override
    @Transactional
    public void deleteByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        educationInfoRepository.deleteByResume(resume);
    }

    private EducationInfoDto toDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResume().getId())
                .institution(info.getInstitution())
                .program(info.getProgram())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .degree(info.getDegree())
                .build();
    }
}