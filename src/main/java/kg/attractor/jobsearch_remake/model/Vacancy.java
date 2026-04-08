package kg.attractor.jobsearch_remake.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vacancy {

    private Integer id;
    private String name;
    private String description;
    private Integer categoryId;
    private double salary;
    private Integer expFrom;
    private Integer expTo;
    private boolean isActive;
    private Integer authorId;
    private LocalDate createdDate;
    private LocalDate updateTime;

}