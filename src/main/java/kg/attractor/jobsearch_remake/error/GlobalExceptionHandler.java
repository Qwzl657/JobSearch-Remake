package kg.attractor.jobsearch_remake.error;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch_remake.exception.ResumeNotFoundException;
import kg.attractor.jobsearch_remake.exception.UserNotFoundException;
import kg.attractor.jobsearch_remake.exception.VacancyNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import kg.attractor.jobsearch_remake.exception.CategoryNotFoundException;
import kg.attractor.jobsearch_remake.exception.ContactTypeNotFoundException;
import kg.attractor.jobsearch_remake.exception.MessageNotFoundException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

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
        log.error("Not found: {}", request.getRequestURI());
        model.addAttribute("status", HttpStatus.NOT_FOUND.value());
        model.addAttribute("reason", HttpStatus.NOT_FOUND.getReasonPhrase() + ": " + e.getMessage());
        model.addAttribute("details", request);
        return "errors/error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException e) {
        log.error("Validation error: {}", e.getMessage());
        String firstError = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(fe -> fe.getField() + ": " + fe.getDefaultMessage())
                .orElse("Validation error");
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseBody(HttpStatus.BAD_REQUEST.value(), firstError));
    }

    @ExceptionHandler(Exception.class)
    public String serverErrorHandler(HttpServletRequest request,
                                     Exception e,
                                     Model model) {
        log.error("Server error on: {}, reason: {}", request.getRequestURI(), e.getMessage());
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        model.addAttribute("reason", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        model.addAttribute("details", request);
        return "errors/error";
    }
}