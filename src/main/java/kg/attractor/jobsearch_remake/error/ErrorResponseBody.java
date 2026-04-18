package kg.attractor.jobsearch_remake.error;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponseBody {
    private String message;
    private String cause;
}