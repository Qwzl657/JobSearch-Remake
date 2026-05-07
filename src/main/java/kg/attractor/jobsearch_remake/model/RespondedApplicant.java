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
    private Long id;

    @Column(name = "resume_id", nullable = false)
    private Long resumeId;

    @Column(name = "vacancy_id", nullable = false)
    private Long vacancyId;

    @Column(name = "confirmation")
    private boolean confirmation;
}