package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoDto {
    private Long id;
    private Long resumeId;

    @NotBlank(message = "{validation.education.institution.blank}")
    private String institution;

    @NotBlank(message = "{validation.education.program.blank}")
    private String program;

    @PastOrPresent(message = "{validation.education.startDate.future}")
    private LocalDateTime startDate;

    @PastOrPresent(message = "{validation.education.endDate.future}")
    private LocalDateTime endDate;

    private String degree;

    @AssertTrue(message = "{validation.education.dates.invalid}")
    public boolean isDatesValid() {
        if (startDate == null || endDate == null) {
            return true;
        }
        return !startDate.isAfter(endDate);
    }
}