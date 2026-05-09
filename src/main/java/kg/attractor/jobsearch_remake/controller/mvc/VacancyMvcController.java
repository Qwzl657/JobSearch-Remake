package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/vacancies")
@RequiredArgsConstructor
public class VacancyMvcController {

    private final VacancyService vacancyService;
    private final UserService userService;

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
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .toList());
            return "vacancies/form";
        }
        UserDto user = userService.getByEmail(auth.getName());
        vacancyDto.setAuthorId(user.getId().intValue());
        vacancyService.create(vacancyDto);
        return "redirect:/profile";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("vacancyDto", vacancyService.getById(id));
        return "vacancies/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,
                       @Valid VacancyDto vacancyDto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("vacancyDto", vacancyDto);
            model.addAttribute("errors", bindingResult.getFieldErrors()
                    .stream()
                    .map(e -> e.getDefaultMessage())
                    .toList());
            return "vacancies/form";
        }
        vacancyService.update(id, vacancyDto);
        return "redirect:/profile";
    }
}