package kg.attractor.jobsearch_remake.dto;

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
    private Integer applicantId;
    private String name;
    private Integer categoryId;
    private double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;
    private List<WorkExperienceInfoDto> workExperienceInfos;
    private List<EducationInfoDto> educationInfos;
    private List<ContactInfoDto> contactInfos;
}
