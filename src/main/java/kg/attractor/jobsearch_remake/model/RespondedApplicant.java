package kg.attractor.jobsearch_remake.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "responded_applicants")
public class RespondedApplicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "resume_id")
    private Integer resumeId;

    @Column(name = "vacancy_id")
    private Integer vacancyId;

    @Column(name = "confirmation")
    private boolean confirmation;
}