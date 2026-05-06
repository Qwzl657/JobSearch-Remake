package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.ContactInfoDto;
import kg.attractor.jobsearch_remake.model.ContactInfo;
import kg.attractor.jobsearch_remake.repository.ContactInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    @Transactional(readOnly = true)
    public List<ContactInfoDto> getByResumeId(Integer resumeId) {
        log.info("Получение контактов для резюме id: {}", resumeId);
        return contactInfoRepository.findByResumeId(resumeId).stream()
                .map(this::toDto)
                .toList();
    }

    public void createForResume(Integer resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        log.info("Создание контактов для резюме id: {}", resumeId);
        dtos.forEach(dto -> contactInfoRepository.save(toModel(resumeId, dto)));
    }

    public void updateForResume(Integer resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null) return;
        log.info("Обновление контактов для резюме id: {}", resumeId);
        contactInfoRepository.deleteByResumeId(resumeId);
        createForResume(resumeId, dtos);
    }

    public void deleteByResumeId(Integer resumeId) {
        log.warn("Удаление контактов для резюме id: {}", resumeId);
        contactInfoRepository.deleteByResumeId(resumeId);
    }

    private ContactInfoDto toDto(ContactInfo info) {
        return ContactInfoDto.builder()
                .id(info.getId().intValue()) // ✅ Long → Integer
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