package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.EducationInfoDto;
import kg.attractor.jobsearch_remake.model.EducationInfo;
import kg.attractor.jobsearch_remake.repository.EducationInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EducationInfoService {

    private final EducationInfoRepository educationInfoRepository;

    public List<EducationInfoDto> getByResumeId(Integer resumeId) {
        log.info("Fetching education info for resume id: {}", resumeId);
        return educationInfoRepository.findByResumeId(resumeId).stream()
                .map(this::toDto)
                .toList();
    }

    public void createForResume(Integer resumeId, List<EducationInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        log.info("Creating education info for resume id: {}", resumeId);
        dtos.forEach(dto -> educationInfoRepository.save(toModel(resumeId, dto)));
    }

    public void updateForResume(Integer resumeId, List<EducationInfoDto> dtos) {
        if (dtos == null) return;
        log.info("Updating education info for resume id: {}", resumeId);
        educationInfoRepository.deleteByResumeId(resumeId);
        createForResume(resumeId, dtos);
    }

    public void deleteByResumeId(Integer resumeId) {
        log.warn("Deleting education info for resume id: {}", resumeId);
        educationInfoRepository.deleteByResumeId(resumeId);
    }

    private EducationInfoDto toDto(EducationInfo info) {
        return EducationInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResumeId())
                .institution(info.getInstitution())
                .program(info.getProgram())
                .startDate(info.getStartDate())
                .endDate(info.getEndDate())
                .degree(info.getDegree())
                .build();
    }

    private EducationInfo toModel(Integer resumeId, EducationInfoDto dto) {
        return EducationInfo.builder()
                .resumeId(resumeId)
                .institution(dto.getInstitution())
                .program(dto.getProgram())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .degree(dto.getDegree())
                .build();
    }
}