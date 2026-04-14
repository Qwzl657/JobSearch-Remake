package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ContactInfoDao;
import kg.attractor.jobsearch_remake.dto.ContactInfoDto;
import kg.attractor.jobsearch_remake.model.ContactInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactInfoService {

    private final ContactInfoDao contactInfoDao;

    public List<ContactInfoDto> getByResumeId(Integer resumeId) {
        log.info("Fetching contact info for resume id: {}", resumeId);
        return contactInfoDao.findByResumeId(resumeId).stream()
                .map(this::toDto)
                .toList();
    }

    public void createForResume(Integer resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        log.info("Creating {} contact entries for resume id: {}", dtos.size(), resumeId);
        dtos.forEach(dto -> contactInfoDao.create(toModel(resumeId, dto)));
    }

    public void updateForResume(Integer resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null) return;
        log.info("Updating contact info for resume id: {}", resumeId);
        contactInfoDao.deleteByResumeId(resumeId);
        createForResume(resumeId, dtos);
    }

    public void deleteByResumeId(Integer resumeId) {
        log.warn("Deleting all contact info for resume id: {}", resumeId);
        contactInfoDao.deleteByResumeId(resumeId);
    }

    private ContactInfoDto toDto(ContactInfo info) {
        return ContactInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResumeId())
                .typeId(info.getTypeId())
                .value(info.getValue())
                .build();
    }

    private ContactInfo toModel(Integer resumeId, ContactInfoDto dto) {
        return ContactInfo.builder()
                .resumeId(resumeId)
                .typeId(dto.getTypeId())
                .value(dto.getValue())
                .build();
    }
}
