package kg.attractor.jobsearch_remake.controller.mvc;

import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/resumes")
@RequiredArgsConstructor
public class ResumeMvcController {

    private final ResumeService resumeService;
    private final UserService userService;

    @GetMapping("/all")
    @PreAuthorize("hasRole('EMPLOYER')")
    public String allResumes(Model model,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        Page<ResumeDto> resumePage = resumeService.getAllPaged(page, size);
        model.addAttribute("resumes", resumePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", resumePage.getTotalPages());
        return "resumes/list";
    }

    @GetMapping("/create")
    @PreAuthorize("hasRole('APPLICANT')")
    public String createPage(Model model) {
        model.addAttribute("resumeDto", new ResumeDto());
        return "resumes/form";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasRole('APPLICANT')")
    public String editPage(@PathVariable Long id,
                           Model model,
                           Authentication auth) {
        ResumeDto resume = resumeService.getById(id);
        UserDto user = userService.getByEmail(auth.getName());
        if (!resume.getApplicantId().equals(user.getId())) {
            return "redirect:/profile";
        }
        model.addAttribute("resumeDto", resume);
        return "resumes/form";
    }

    @PostMapping("/{id}/delete")
    @PreAuthorize("hasRole('APPLICANT')")
    public String delete(@PathVariable Long id, Authentication auth) {
        ResumeDto existing = resumeService.getById(id);
        UserDto user = userService.getByEmail(auth.getName());
        if (!existing.getApplicantId().equals(user.getId())) {
            return "redirect:/profile";
        }
        resumeService.delete(id);
        return "redirect:/profile";
    }
}