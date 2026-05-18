package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.ResponseService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyMvcController {

    private final VacancyService vacancyService;
    private final UserService userService;
    private final ResumeService resumeService;
    private final ResponseService responseService;

    @GetMapping
    public String vacanciesList(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                @RequestParam(defaultValue = "date") String sort) {
        Page<VacancyDto> vacanciesPage = vacancyService.getActivePaged(page, size, sort);
        model.addAttribute("vacancies", vacanciesPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", vacanciesPage.getTotalPages());
        model.addAttribute("sort", sort);
        return "vacancies/list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("vacancyDto", new VacancyDto());
        return "vacancies/form";
    }

    @PostMapping("/create")
    public String create(@Valid VacancyDto vacancyDto,
                         BindingResult bindingResult,
                         Model model,
                         Authentication auth) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyDto", vacancyDto);
            model.addAttribute("errors", bindingResult.getFieldErrors()
                    .stream().map(e -> e.getDefaultMessage()).toList());
            return "vacancies/form";
        }
        UserDto user = userService.getByEmail(auth.getName());
        vacancyDto.setAuthorId(user.getId());
        vacancyService.create(vacancyDto);
        return "redirect:/profile";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Long id, Model model, Authentication auth) {
        VacancyDto vacancy = vacancyService.getById(id);
        UserDto user = userService.getByEmail(auth.getName());
        if (!vacancy.getAuthorId().equals(user.getId())) {
            return "redirect:/vacancies";
        }
        model.addAttribute("vacancyDto", vacancy);
        return "vacancies/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id,
                       @Valid VacancyDto vacancyDto,
                       BindingResult bindingResult,
                       Model model,
                       Authentication auth) {
        VacancyDto existing = vacancyService.getById(id);
        UserDto user = userService.getByEmail(auth.getName());
        if (!existing.getAuthorId().equals(user.getId())) {
            return "redirect:/vacancies";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyDto", vacancyDto);
            model.addAttribute("errors", bindingResult.getFieldErrors()
                    .stream().map(e -> e.getDefaultMessage()).toList());
            return "vacancies/form";
        }
        vacancyService.update(id, vacancyDto);
        return "redirect:/profile";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, Authentication auth) {
        VacancyDto existing = vacancyService.getById(id);
        UserDto user = userService.getByEmail(auth.getName());
        if (!existing.getAuthorId().equals(user.getId())) {
            return "redirect:/vacancies";
        }
        vacancyService.delete(id);
        return "redirect:/profile";
    }

    @GetMapping("/{id}/respond")
    public String respondPage(@PathVariable Long id,
                              Model model,
                              Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        List<ResumeDto> resumes = resumeService.getByApplicant(user.getId());
        model.addAttribute("vacancy", vacancyService.getById(id));
        model.addAttribute("resumes", resumes);
        return "vacancies/respond";
    }

    @PostMapping("/{id}/respond")
    public String respond(@PathVariable Long id,
                          @RequestParam Long resumeId,
                          Model model) {
        boolean success = responseService.respond(resumeId, id);
        if (!success) {
            model.addAttribute("alreadyResponded", true);
            UserDto user = userService.getByEmail(
                    SecurityContextHolder.getContext().getAuthentication().getName()
            );
            model.addAttribute("vacancy", vacancyService.getById(id));
            model.addAttribute("resumes", resumeService.getByApplicant(user.getId()));
            return "vacancies/respond";
        }
        return "redirect:/vacancies";
    }

    @GetMapping("/{id}/responses")
    public String responses(@PathVariable Long id,
                            Model model,
                            Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        VacancyDto vacancy = vacancyService.getById(id);
        if (!vacancy.getAuthorId().equals(user.getId())) {
            return "redirect:/vacancies";
        }
        List<UserDto> applicants = responseService.getUsersByVacancy(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("applicants", applicants);
        return "vacancies/responses";
    }
}