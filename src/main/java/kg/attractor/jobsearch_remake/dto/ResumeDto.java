package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Long id;
    private Long applicantId;

    @NotBlank(message = "{validation.resume.name.blank}")
    private String name;

    private Integer categoryId;

    @PositiveOrZero(message = "{validation.resume.salary.positive}")
    private Double salary;

    private boolean active;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;


    @Valid
    private List<WorkExperienceInfoDto> workExperienceInfos;

    @Valid
    private List<EducationInfoDto> educationInfos;

    @Valid
    private List<ContactInfoDto> contactInfos;
}