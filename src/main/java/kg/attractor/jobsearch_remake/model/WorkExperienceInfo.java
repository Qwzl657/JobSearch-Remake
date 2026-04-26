package kg.attractor.jobsearch_remake.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_experience_info")
public class WorkExperienceInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resume_id")
    private Integer resumeId;

    @Column(name = "years")
    private Integer years;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "responsibilities")
    private String responsibilities;
}