package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.model.RespondedApplicant;
import kg.attractor.jobsearch_remake.repository.RespondedApplicantRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {

    private final RespondedApplicantRepository respondedApplicantRepository;
    private final UserService userService;
    private final VacancyService vacancyService;

    public List<VacancyDto> getVacanciesByUser(Long userId) {
        log.info("Fetching vacancies for user id: {}", userId);
        return respondedApplicantRepository.findByResumeId(userId.intValue()).stream()
                .map(r -> vacancyService.getById(r.getVacancyId()))
                .toList();
    }

    public List<UserDto> getUsersByVacancy(Long vacancyId) {
        log.info("Fetching applicants for vacancy id: {}", vacancyId);
        return respondedApplicantRepository.findByVacancyId(vacancyId.intValue()).stream()
                .map(r -> userService.findById(r.getResumeId()))
                .toList();
    }

    public void respond(Long userId, Long vacancyId) {
        log.info("User id: {} responding to vacancy id: {}", userId, vacancyId);
        RespondedApplicant response = RespondedApplicant.builder()
                .resumeId(userId.intValue())
                .vacancyId(vacancyId.intValue())
                .confirmation(false)
                .build();
        respondedApplicantRepository.save(response);
    }
}