package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.ContactInfoDto;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.model.ContactInfo;
import kg.attractor.jobsearch_remake.model.ContactType;
import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.repository.ContactInfoRepository;
import kg.attractor.jobsearch_remake.repository.ContactTypeRepository;
import kg.attractor.jobsearch_remake.repository.ResumeRepository;
import kg.attractor.jobsearch_remake.service.ContactInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactInfoServiceImpl implements ContactInfoService {

    private final ContactInfoRepository contactInfoRepository;
    private final ResumeRepository resumeRepository;
    private final ContactTypeRepository contactTypeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ContactInfoDto> getByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        return contactInfoRepository.findByResume(resume).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public void createForResume(Long resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null || dtos.isEmpty()) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        dtos.forEach(dto -> {
            ContactType type = dto.getTypeId() != null
                    ? contactTypeRepository.findById(dto.getTypeId()).orElse(null)
                    : null;
            contactInfoRepository.save(ContactInfo.builder()
                    .resume(resume).type(type).value(dto.getValue()).build());
        });
    }

    @Override
    @Transactional
    public void updateForResume(Long resumeId, List<ContactInfoDto> dtos) {
        if (dtos == null) return;
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        contactInfoRepository.deleteByResume(resume);
        createForResume(resumeId, dtos);
    }

    @Override
    @Transactional
    public void deleteByResumeId(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        contactInfoRepository.deleteByResume(resume);
    }

    private ContactInfoDto toDto(ContactInfo info) {
        return ContactInfoDto.builder()
                .id(info.getId())
                .resumeId(info.getResume().getId())
                .typeId(info.getType() != null ? info.getType().getId() : null)
                .value(info.getValue())
                .build();
    }
}