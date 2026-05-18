package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoDto {
    private Long id;
    private Long resumeId;

    @Min(value = 0, message = "{validation.workexp.years.min}")
    @Max(value = 60, message = "{validation.workexp.years.max}")
    private Integer years;

    @NotBlank(message = "{validation.workexp.company.blank}")
    private String companyName;

    @NotBlank(message = "{validation.workexp.position.blank}")
    private String position;

    private String responsibilities;
}