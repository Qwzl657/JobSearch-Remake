package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;

import java.util.List;

public interface ResponseService {
    List<VacancyDto> getVacanciesByResume(Long resumeId);
    List<UserDto> getUsersByVacancy(Long vacancyId);
    boolean respond(Long resumeId, Long vacancyId);
}