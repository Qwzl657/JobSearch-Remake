package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.ContactInfoDto;

import java.util.List;

public interface ContactInfoService {
    List<ContactInfoDto> getByResumeId(Long resumeId);
    void createForResume(Long resumeId, List<ContactInfoDto> dtos);
    void updateForResume(Long resumeId, List<ContactInfoDto> dtos);
    void deleteByResumeId(Long resumeId);
}