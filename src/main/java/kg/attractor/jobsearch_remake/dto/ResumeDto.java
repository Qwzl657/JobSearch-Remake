package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Integer id;

    @NotNull(message = "Applicant is required")
    private Integer applicantId;

    @NotBlank(message = "Resume name cannot be blank")
    private String name;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    @Positive(message = "Salary must be positive")
    private double salary;

    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
    private List<WorkExperienceInfoDto> workExperienceInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
}
