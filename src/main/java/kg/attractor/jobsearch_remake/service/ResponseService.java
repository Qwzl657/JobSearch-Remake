package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import kg.attractor.jobsearch_remake.repository.RespondedApplicantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {

    private final RespondedApplicantRepository respondedApplicantRepository;
    private final UserService userService;
    private final VacancyService vacancyService;
    private final ResumeService resumeService;

    public List<VacancyDto> getVacanciesByResume(Long resumeId) {
        log.info("Получение вакансий для резюме id: {}", resumeId);
        return respondedApplicantRepository.findByResumeId(resumeId.intValue()).stream()
                .map(r -> vacancyService.getById(r.getVacancyId()))
                .toList();
    }

    public List<UserDto> getUsersByVacancy(Long vacancyId) {
        log.info("Получение соискателей для вакансии id: {}", vacancyId);
        return respondedApplicantRepository.findByVacancyId(vacancyId.intValue()).stream()
                .map(r -> {
                    ResumeDto resume = resumeService.getById(r.getResumeId());
                    return userService.findById(resume.getApplicantId());
                })
                .toList();
    }


    @Transactional
    public void respond(Long resumeId, Long vacancyId) {
        log.info("Отклик на вакансию id: {} с резюме id: {}", vacancyId, resumeId);
        RespondedApplicant response = RespondedApplicant.builder()
                .resumeId(resumeId.intValue())
                .vacancyId(vacancyId.intValue())
                .confirmation(false)
                .build();
        respondedApplicantRepository.save(response);
    }
}