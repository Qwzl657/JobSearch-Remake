package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.WorkExperienceInfoDto;
import kg.attractor.jobsearch_remake.model.WorkExperienceInfo;
import kg.attractor.jobsearch_remake.repository.WorkExperienceInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class WorkExperienceInfoService {

    private final WorkExperienceInfoRepository workExperienceInfoRepository;

    @Transactional(readOnly = true)
    public List<WorkExperienceInfoDto> getByResumeId(Integer resumeId) {
        log.info("Получение опыта работы для резюме id: {}", resumeId);
        return workExperienceInfoRepository.findByResumeId(resumeId).stream()
                .map(this::toDto)
                .toList();
    }

    public void createForResume(Integer resumeId, List<WorkExperienceInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        log.info("Создание опыта работы для резюме id: {}", resumeId);
        dtos.forEach(dto -> workExperienceInfoRepository.save(toModel(resumeId, dto)));
    }


    public void updateForResume(Integer resumeId, List<WorkExperienceInfoDto> dtos) {
        if (dtos == null) return;
        log.info("Обновление опыта работы для резюме id: {}", resumeId);
        workExperienceInfoRepository.deleteByResumeId(resumeId);
        createForResume(resumeId, dtos);
    }

    public void deleteByResumeId(Integer resumeId) {
        log.warn("Удаление опыта работы для резюме id: {}", resumeId);
        workExperienceInfoRepository.deleteByResumeId(resumeId);
    }

    private WorkExperienceInfoDto toDto(WorkExperienceInfo info) {
        return WorkExperienceInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResumeId())
                .years(info.getYears())
                .companyName(info.getCompanyName())
                .position(info.getPosition())
                .responsibilities(info.getResponsibilities())
                .build();
    }

    private WorkExperienceInfo toModel(Integer resumeId, WorkExperienceInfoDto dto) {
        return WorkExperienceInfo.builder()
                .resumeId(resumeId)
                .years(dto.getYears())
                .companyName(dto.getCompanyName())
                .position(dto.getPosition())
                .responsibilities(dto.getResponsibilities())
                .build();
    }
}