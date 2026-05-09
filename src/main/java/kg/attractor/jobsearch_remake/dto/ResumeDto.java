package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
    private Integer id;

    private Long applicantId;

    @NotBlank(message = "{validation.resume.name.blank}")
    private String name;

    private Integer categoryId;

    @Positive(message = "{validation.resume.salary.positive}")
    private double salary;

    private boolean active;

    private LocalDateTime createdDate;
    private LocalDateTime updateTime;
    private List<WorkExperienceInfoDto> workExperienceInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
}