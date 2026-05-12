package kg.attractor.jobsearch_remake.service.impl;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.exception.VacancyNotFoundException;
import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import kg.attractor.jobsearch_remake.model.Resume;
import kg.attractor.jobsearch_remake.model.Vacancy;
import kg.attractor.jobsearch_remake.repository.RespondedApplicantRepository;
import kg.attractor.jobsearch_remake.repository.ResumeRepository;
import kg.attractor.jobsearch_remake.repository.VacancyRepository;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.ResponseService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final RespondedApplicantRepository respondedApplicantRepository;
    private final ResumeRepository resumeRepository;
    private final VacancyRepository vacancyRepository;
    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @Override
    public List<VacancyDto> getVacanciesByResume(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        return respondedApplicantRepository.findByResume(resume).stream()
                .map(r -> vacancyService.getById(r.getVacancy().getId()))
                .toList();
    }

    @Override
    public List<UserDto> getUsersByVacancy(Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(VacancyNotFoundException::new);
        return respondedApplicantRepository.findByVacancy(vacancy).stream()
                .map(r -> {
                    ResumeDto resume = resumeService.getById(r.getResume().getId());
                    return userService.findById(resume.getApplicantId());
                })
                .toList();
    }

    @Override
    @Transactional
    public boolean respond(Long resumeId, Long vacancyId) {
        Resume resume = resumeRepository.findById(resumeId).orElseThrow(ResumeNotFoundException::new);
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(VacancyNotFoundException::new);
        if (respondedApplicantRepository.existsByResumeAndVacancy(resume, vacancy)) {
            log.warn("Повторный отклик: резюме {} на вакансию {}", resumeId, vacancyId);
            return false;
        }
        respondedApplicantRepository.save(RespondedApplicant.builder()
                .resume(resume)
                .vacancy(vacancy)
                .confirmation(false)
                .build());
        log.info("Отклик создан: резюме {} на вакансию {}", resumeId, vacancyId);
        return true;
    }
}