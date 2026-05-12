package kg.attractor.jobsearch_remake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WorkExperienceInfoDto {
    private Long id;
    private Long resumeId;

    @Min(value = 0, message = "{validation.workexp.years.min}")
    private Integer years;

    @NotBlank(message = "{validation.workexp.company.blank}")
    private String companyName;

    @NotBlank(message = "{validation.workexp.position.blank}")
    private String position;

    private String responsibilities;
}