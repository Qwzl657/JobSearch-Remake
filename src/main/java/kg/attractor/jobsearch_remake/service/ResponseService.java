package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResponseDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseService {

    private final ResponseDao responseDao;
    private final UserService userService;
    private final VacancyService vacancyService;

    public List<VacancyDto> getVacanciesByUser(Long userId) {
        log.info("Fetching vacancies for user id: {}", userId);
        return responseDao.findVacanciesByUserId(userId).stream()
                .map(v -> vacancyService.getById(v.getId()))
                .toList();
    }

    public List<UserDto> getUsersByVacancy(Long vacancyId) {
        log.info("Fetching applicants for vacancy id: {}", vacancyId);
        return responseDao.findUsersByVacancyId(vacancyId).stream()
                .map(u -> userService.findById(u.getId()))
                .toList();
    }

    public void respond(Long userId, Long vacancyId) {
        log.info("User id: {} responding to vacancy id: {}", userId, vacancyId);
        responseDao.respond(userId, vacancyId);
    }
}