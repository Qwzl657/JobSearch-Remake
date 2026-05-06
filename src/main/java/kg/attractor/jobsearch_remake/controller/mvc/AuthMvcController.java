package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.UserCreateDto;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
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

        model.addAttribute("userDto", new UserCreateDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserCreateDto userDto,
                           BindingResult bindingResult,
                           Model model,
                           HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/register";
        }

        userService.create(userDto);
        log.info("Зарегистрирован новый пользователь: {}", userDto.getEmail());

        var authorities = List.of(
                new SimpleGrantedAuthority("ROLE_" + userDto.getAccountType())
        );
        var auth = new UsernamePasswordAuthenticationToken(
                userDto.getEmail(), null, authorities
        );
        SecurityContextHolder.getContext().setAuthentication(auth);
        request.getSession().setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );

        if (userDto.getAccountType().equals("EMPLOYER")) {
            return "redirect:/resumes/all";
        }
        return "redirect:/vacancies";
    }
}