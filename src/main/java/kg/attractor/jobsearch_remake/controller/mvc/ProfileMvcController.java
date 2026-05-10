package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.ResumeDto;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.dto.VacancyDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileMvcController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profilePage(Model model,
                              Authentication auth,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "5") int size) {
        UserDto user = userService.getByEmail(auth.getName());
        model.addAttribute("user", user);

        if (user.getAccountType().equals("APPLICANT")) {
            Page<ResumeDto> resumePage = resumeService.getByApplicantPaged(
                    user.getId().intValue(), page, size);
            model.addAttribute("resumes", resumePage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", resumePage.getTotalPages());
        } else {
            Page<VacancyDto> vacancyPage = vacancyService.getByAuthorPaged(
                    user.getId().intValue(), page, size);
            model.addAttribute("vacancies", vacancyPage.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", vacancyPage.getTotalPages());
        }
        return "profile/profile";
    }

    @GetMapping("/edit")
    public String editPage(Model model, Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        model.addAttribute("user", user);
        return "profile/edit";
    }

    @PostMapping("/edit")
    public String editProfile(@Valid @ModelAttribute UserDto userDto,
                              BindingResult bindingResult,
                              @RequestParam(name = "avatarFile", required = false) MultipartFile avatar,
                              Authentication auth) throws IOException {

        if (bindingResult.hasErrors()) {
            return "profile/edit";
        }
        UserDto user = userService.getByEmail(auth.getName());

        if (avatar != null && !avatar.isEmpty()) {
            String filename = UUID.randomUUID() + "_" + avatar.getOriginalFilename();
            Path path = Paths.get("uploads/avatars/" + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, avatar.getBytes());
            userDto.setAvatar(filename);
            log.info("Аватар обновлён для пользователя: {}", auth.getName());
        } else {
            userDto.setAvatar(user.getAvatar());
        }
        userService.update(user.getId(), userDto);
        return "redirect:/profile";
    }
}