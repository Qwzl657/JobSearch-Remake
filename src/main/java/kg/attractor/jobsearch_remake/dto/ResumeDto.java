package kg.attractor.jobsearch_remake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeDto {
    private Integer id;
    private String name;
    private Integer categoryId;
    private double salary;
    private boolean isActive;
}
