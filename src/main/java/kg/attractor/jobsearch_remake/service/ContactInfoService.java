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
public class ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;

    @Transactional(readOnly = true)
    public List<ContactInfoDto> getByResumeId(Long resumeId) {
        log.info("Получение контактов для резюме id: {}", resumeId);
        return contactInfoRepository.findByResumeId(resumeId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public void createForResume(Long resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        log.info("Создание контактов для резюме id: {}", resumeId);
        dtos.forEach(dto -> contactInfoRepository.save(toModel(resumeId, dto)));
    }

    @Transactional
    public void updateForResume(Long resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null) return;
        log.info("Обновление контактов для резюме id: {}", resumeId);
        contactInfoRepository.deleteByResumeId(resumeId);
        createForResume(resumeId, dtos);
    }

    @Transactional
    public void deleteByResumeId(Long resumeId) {
        log.warn("Удаление контактов для резюме id: {}", resumeId);
        contactInfoRepository.deleteByResumeId(resumeId);
    }

    private ContactInfoDto toDto(ContactInfo info) {
        return ContactInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResumeId())
                .typeId(info.getTypeId())
                .value(info.getValue())
                .build();
    }

    private ContactInfo toModel(Long resumeId, ContactInfoDto dto) {
        return ContactInfo.builder()
                .resumeId(resumeId)
                .typeId(dto.getTypeId())
                .value(dto.getValue())
                .build();
    }
}