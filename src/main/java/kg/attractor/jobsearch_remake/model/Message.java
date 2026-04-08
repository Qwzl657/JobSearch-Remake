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
public class Message {

    private Integer id;
    private Integer respondedApplicants;
    private String content;
    private LocalDate timestamp;

}
