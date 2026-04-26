package kg.attractor.jobsearch_remake.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "responded_applicants")
    private Integer respondedApplicants;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private LocalDate timestamp;
}