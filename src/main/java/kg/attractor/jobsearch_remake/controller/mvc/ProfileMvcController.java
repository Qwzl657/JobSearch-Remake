package kg.attractor.jobsearch_remake.controller.mvc;

import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileMvcController {

    private final UserService userService;
    private final ResumeService resumeService;
    private final VacancyService vacancyService;

    @GetMapping
    public String profilePage(Model model, Authentication auth) {
        UserDto user = userService.getByEmail(auth.getName());
        model.addAttribute("user", user);

        if (user.getAccountType().equals("APPLICANT")) {
            model.addAttribute("resumes", resumeService.getByApplicant(user.getId().intValue()));
        } else {
            model.addAttribute("vacancies", vacancyService.getByAuthor(user.getId().intValue()));
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
    public String editProfile(@ModelAttribute UserDto userDto,
                              @RequestParam(required = false) MultipartFile avatar,
                              Authentication auth) throws IOException {
        UserDto user = userService.getByEmail(auth.getName());
        if (avatar != null && !avatar.isEmpty()) {
            String filename = avatar.getOriginalFilename();
            Path path = Paths.get("uploads/avatars/" + filename);
            Files.createDirectories(path.getParent());
            Files.write(path, avatar.getBytes());
            userDto.setAvatar(filename);
        } else {
            userDto.setAvatar(user.getAvatar());
        }
        userService.update(user.getId().intValue(), userDto);
        return "redirect:/profile";
    }
}