package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.WorkExperienceInfoDto;

import java.util.List;

public interface WorkExperienceInfoService {
    List<WorkExperienceInfoDto> getByResumeId(Long resumeId);
    void createForResume(Long resumeId, List<WorkExperienceInfoDto> dtos);
    void updateForResume(Long resumeId, List<WorkExperienceInfoDto> dtos);
    void deleteByResumeId(Long resumeId);
}