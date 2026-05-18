package kg.attractor.jobsearch_remake.error;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch_remake.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice(basePackages = "kg.attractor.jobsearch_remake.controller.mvc")
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({
            UserNotFoundException.class,
            VacancyNotFoundException.class,
            ResumeNotFoundException.class,
            CategoryNotFoundException.class,
            MessageNotFoundException.class,
            ContactTypeNotFoundException.class
    })
    public String notFoundHandler(HttpServletRequest request,
                                  RuntimeException e,
                                  Model model) {
        log.error("Not found: {} — {}", request.getRequestURI(), e.getMessage());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", e.getMessage());
        return "errors/error";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String serverErrorHandler(HttpServletRequest request,
                                     Exception e,
                                     Model model) {
        log.error("Server error on: {} — причина: {} — тип: {}",
                request.getRequestURI(),
                e.getMessage(),
                e.getClass().getName());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("reason", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        return "errors/error";
    }
}