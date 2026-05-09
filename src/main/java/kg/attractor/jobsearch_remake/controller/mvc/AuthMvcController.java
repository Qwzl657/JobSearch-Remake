package kg.attractor.jobsearch_remake.controller.mvc;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import kg.attractor.jobsearch_remake.dto.UserCreateDto;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.model.User;
import kg.attractor.jobsearch_remake.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Slf4j
@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthMvcController {

    private final UserService userService;
    private final MessageSource messageSource;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid UserCreateDto userCreateDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreateDto", userCreateDto);
            return "auth/register";
        }

        userService.create(userCreateDto);
        log.info("Зарегистрирован новый пользователь: {}", userCreateDto.getEmail());

        userService.autoLogin(userCreateDto.getEmail());

        if ("EMPLOYER".equals(userCreateDto.getAccountType())) {
            return "redirect:/resumes/all";
        }
        return "redirect:/vacancies";
    }

    @GetMapping("/forgot-password")
    public String showForgotPassword() {
        return "auth/forgot_password_form";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(HttpServletRequest request,
                                        Model model,
                                        Locale locale) {
        try {
            userService.makeResetPwdLink(request);
            model.addAttribute("message",
                    messageSource.getMessage("auth.forgot.success", null, locale));
        } catch (UserNotFoundException e) {
            model.addAttribute("error",
                    messageSource.getMessage("auth.forgot.error.notfound", null, locale));
        } catch (UnsupportedEncodingException e) {
            model.addAttribute("error",
                    messageSource.getMessage("auth.forgot.error.mail", null, locale));
        } catch (MessagingException e) {
            model.addAttribute("error",
                    messageSource.getMessage("auth.forgot.error.mail", null, locale));
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam String token, Model model, Locale locale) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UserNotFoundException e) {
            model.addAttribute("error",
                    messageSource.getMessage("auth.reset.error", null, locale));
        }
        return "auth/reset_password_form";
    }

    @PostMapping("/reset-password")
    public String processResetPassword(HttpServletRequest request,
                                       Model model,
                                       Locale locale) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message",
                    messageSource.getMessage("auth.reset.success", null, locale));
        } catch (UserNotFoundException e) {
            model.addAttribute("error",
                    messageSource.getMessage("auth.reset.error", null, locale));
        }
        return "auth/reset_password_form";
    }
}