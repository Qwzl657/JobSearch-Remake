package kg.attractor.jobsearch_remake.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public String notFound(HttpServletRequest request, Model model) {
        log.error("Элемент не найден: {}", request.getRequestURI());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI()); // ✅ только URI
        return "/errors/error";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String badRequest(HttpServletRequest request, Model model) {
        log.error("Неверный запрос: {}", request.getRequestURI());
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", HttpStatus.BAD_REQUEST.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        return "/errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validationError(HttpServletRequest request,
                                  MethodArgumentNotValidException ex,
                                  Model model) {
        log.error("Ошибка валидации на: {}", request.getRequestURI());
        String firstError = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .orElse("Ошибка валидации");
        model.addAttribute("status", HttpStatus.BAD_REQUEST.value());
        model.addAttribute("reason", firstError);
        model.addAttribute("details", request.getRequestURI());
        return "/errors/error";
    }

    @ExceptionHandler(Exception.class)
    public String serverError(HttpServletRequest request,
                              Exception ex,
                              Model model) {
        log.error("Внутренняя ошибка сервера на: {}, причина: {}",
                request.getRequestURI(), ex.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("reason", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        model.addAttribute("details", request.getRequestURI());
        return "/errors/error";
    }
}