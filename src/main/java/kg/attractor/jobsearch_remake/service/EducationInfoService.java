package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.EducationInfoDto;

import java.util.List;

public interface EducationInfoService {
    List<EducationInfoDto> getByResumeId(Long resumeId);
    void createForResume(Long resumeId, List<EducationInfoDto> dtos);
    void updateForResume(Long resumeId, List<EducationInfoDto> dtos);
    void deleteByResumeId(Long resumeId);
}