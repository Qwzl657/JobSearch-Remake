package kg.attractor.jobsearch_remake.controller.mvc;
import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.UserDto;
import kg.attractor.jobsearch_remake.service.ResumeService;
import kg.attractor.jobsearch_remake.service.UserService;
import kg.attractor.jobsearch_remake.service.VacancyService;
import lombok.RequiredArgsConstructor;
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
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthMvcController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserDto userDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }
        userService.create(userDto);
        return "redirect:/auth/login";
    }
}