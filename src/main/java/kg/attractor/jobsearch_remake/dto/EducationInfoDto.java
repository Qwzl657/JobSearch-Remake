package kg.attractor.jobsearch_remake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EducationInfoDto {
    private Integer id;
    private Integer resumeId;

    @NotBlank(message = "{validation.education.institution.blank}")
    private String institution;

    @NotBlank(message = "{validation.education.program.blank}")
    private String program;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String degree;
}
