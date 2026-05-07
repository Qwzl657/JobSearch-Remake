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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthMvcController {

    private final UserService userService;

    @GetMapping("login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("register")
    public String register(Model model) {
        model.addAttribute("userCreateDto", new UserCreateDto());
        return "auth/register";
    }

    @PostMapping("register")
    public String register(@Valid UserCreateDto userCreateDto,
                           BindingResult bindingResult,
                           Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userCreateDto", userCreateDto);
            return "auth/register";
        }
        userService.create(userCreateDto);
        return "redirect:/auth/login";
    }

    @GetMapping("forgot-password")
    public String showForgotPassword() {
        return "auth/forgot_password_form";
    }

    @PostMapping("forgot-password")
    public String processForgotPassword(HttpServletRequest request, Model model) {
        try {
            userService.makeResetPwdLink(request);
            model.addAttribute("message", "Ссылка для сброса пароля отправлена на вашу почту.");
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Пользователь с таким email не найден.");
        } catch (UnsupportedEncodingException e) {
            model.addAttribute("error", "Ошибка кодировки.");
        } catch (MessagingException e) {
            model.addAttribute("error", "Ошибка при отправке письма.");
        }
        return "auth/forgot_password_form";
    }

    @GetMapping("reset-password")
    public String showResetPassword(@RequestParam String token, Model model) {
        try {
            userService.getByResetPasswordToken(token);
            model.addAttribute("token", token);
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Неверный или устаревший токен.");
        }
        return "auth/reset_password_form";
    }

    @PostMapping("reset-password")
    public String processResetPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");
        try {
            User user = userService.getByResetPasswordToken(token);
            userService.updatePassword(user, password);
            model.addAttribute("message", "Пароль успешно изменён.");
        } catch (UserNotFoundException e) {
            model.addAttribute("error", "Неверный токен.");
        }
        return "auth/reset_password_form";
    }
}