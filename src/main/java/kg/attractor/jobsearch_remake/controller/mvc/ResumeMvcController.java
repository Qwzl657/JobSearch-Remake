package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeMvcController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping
    public String resumesList(Model model) {
        model.addAttribute("resumes", resumeService.getAllDto());
        return "resumes/list";
    }

    @GetMapping("/all")
    public String allResumes(Model model) {
        model.addAttribute("resumes", resumeService.getAllDto());
        return "resumes/list";
    }

    @GetMapping("/create")
    public String createPage(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        return "resumes/form";
    }

    @PostMapping("/create")
    public String create(@Valid ResumeDto resumeDto,
                         BindingResult bindingResult,
                         Model model,
                         Authentication auth) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            return "resumes/form";
        }
        UserDto user = userService.getByEmail(auth.getName());
        resumeDto.setApplicantId(user.getId().intValue());
        resumeService.create(resumeDto);
        return "redirect:/profile";
    }

    @GetMapping("/{id}/edit")
    public String editPage(@PathVariable Integer id, Model model) {
        model.addAttribute("resumeDto", resumeService.getById(id));
        return "resumes/form";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Integer id,
                       @Valid ResumeDto resumeDto,
                       BindingResult bindingResult,
                       Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("resumeDto", resumeDto);
            return "resumes/form";
        }
        resumeService.update(id, resumeDto);
        return "redirect:/profile";
    }
}