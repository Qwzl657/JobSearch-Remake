package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Integer id;

    @NotBlank(message = "Vacancy name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Category is required")
    private Integer categoryId;

    @Positive(message = "Salary must be positive")
    private double salary;

    @Min(value = 0, message = "Experience from cannot be negative")
    private Integer expFrom;

    @Min(value = 0, message = "Experience to cannot be negative")
    private Integer expTo;

    private boolean isActive;

    @NotNull(message = "Author is required")
    private Integer authorId;

    private LocalDate createdDate;
    private LocalDate updateTime;
}