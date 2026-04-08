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
public class Resume {

    private Integer id;
    private Integer applicantId;
    private String name;
    private int categoryId;
    private double salary;
    private boolean isActive;
    private LocalDate createdDate;
    private LocalDate updateTime;

}