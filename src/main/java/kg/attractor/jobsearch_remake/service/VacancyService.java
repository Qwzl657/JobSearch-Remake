package kg.attractor.jobsearch_remake.service;

import kg.attractor.jobsearch_remake.dao.ResponseDao;
import kg.attractor.jobsearch_remake.dao.VacancyDao;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.model.Vacancy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {

    private final VacancyDao vacancyDao;
    private final ResponseDao responseDao;

    public List<VacancyDto> getAllDto() {
        return vacancyDao.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    public List<VacancyDto> getByCategory(Integer categoryId) {
        return vacancyDao.findByCategory(categoryId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<VacancyDto> getActive() {
        return vacancyDao.getActive().stream()
                .map(this::toDto)
                .toList();
    }

    public List<UserDto> getApplicants(Long vacancyId) {
        return responseDao.findUsersByVacancyId(vacancyId).stream()
                .map(this::toUserDto)
                .toList();
    }

    public void respond(Long userId, Long vacancyId) {
        responseDao.respond(userId, vacancyId);
    }

    public void create(VacancyDto dto) {
        Vacancy v = Vacancy.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.isActive())
                .authorId(dto.getAuthorId())
                .build();

        vacancyDao.create(v);
    }

    public void update(Integer id, VacancyDto dto) {
        Vacancy v = Vacancy.builder()
                .id(id)
                .name(dto.getName())
                .description(dto.getDescription())
                .categoryId(dto.getCategoryId())
                .salary(dto.getSalary())
                .expFrom(dto.getExpFrom())
                .expTo(dto.getExpTo())
                .isActive(dto.isActive())
                .build();

        vacancyDao.update(v);
    }

    public void delete(Integer id) {
        vacancyDao.delete(id);
    }

    private VacancyDto toDto(Vacancy v) {
        return VacancyDto.builder()
                .id(v.getId())
                .name(v.getName())
                .description(v.getDescription())
                .categoryId(v.getCategoryId())
                .salary(v.getSalary())
                .expFrom(v.getExpFrom())
                .expTo(v.getExpTo())
                .isActive(v.isActive())
                .authorId(v.getAuthorId())
                .build();
    }

    private UserDto toUserDto(User u) {
        return UserDto.builder()
                .id(u.getId().longValue())
                .username(u.getName() + " " + u.getSurname())
                .email(u.getEmail())
                .phoneNumber(u.getPhoneNumber())
                .build();
    }
}