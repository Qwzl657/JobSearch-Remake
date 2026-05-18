package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VacancyDto {
    private Long id;

    @NotBlank(message = "{validation.vacancy.name.blank}")
    private String name;

    @NotBlank(message = "{validation.vacancy.description.blank}")
    private String description;

    private Integer categoryId;

    @PositiveOrZero(message = "{validation.vacancy.salary.positive}")
    @DecimalMax(value = "9999999", message = "{validation.vacancy.salary.max}")
    private Double salary;

    @Min(value = 0, message = "{validation.vacancy.expFrom.min}")
    @Max(value = 60, message = "{validation.vacancy.expFrom.max}")
    private Integer expFrom;

    @Min(value = 0, message = "{validation.vacancy.expTo.min}")
    @Max(value = 60, message = "{validation.vacancy.expTo.max}")
    private Integer expTo;

    private boolean active;
    private Long authorId;
    private LocalDateTime createdDate;
    private LocalDateTime updateTime;

    @AssertTrue(message = "{validation.vacancy.exp.invalid}")
    public boolean isExpRangeValid() {
        if (expFrom == null || expTo == null) {
            return true;
        }
        return expFrom <= expTo;
    }
}