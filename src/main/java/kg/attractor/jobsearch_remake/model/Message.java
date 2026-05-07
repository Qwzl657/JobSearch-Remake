package kg.attractor.jobsearch_remake.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "responded_applicants", nullable = false)
    private Long respondedApplicants;

    @Column(name = "content", nullable = false, length = 1000)
    private String content;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
}