package kg.attractor.jobsearch_remake.error;

import jakarta.servlet.http.HttpServletRequest;
import kg.attractor.jobsearch_remake.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@Slf4j
@RestControllerAdvice(basePackages = "kg.attractor.jobsearch_remake.controller")
public class RestExceptionHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            VacancyNotFoundException.class,
            ResumeNotFoundException.class,
            CategoryNotFoundException.class,
            MessageNotFoundException.class,
            ContactTypeNotFoundException.class
    })
    public ResponseEntity<Map<String, Object>> notFoundHandler(HttpServletRequest request,
                                                               RuntimeException e) {
        log.error("REST Not found: {}", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "error", "Not Found",
                "message", e.getMessage() != null ? e.getMessage() : "Resource not found",
                "path", request.getRequestURI()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> serverErrorHandler(HttpServletRequest request,
                                                                  Exception e) {
        log.error("REST Server error on: {}, reason: {}", request.getRequestURI(), e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", "Internal Server Error",
                "message", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "path", request.getRequestURI()
        ));
    }
}