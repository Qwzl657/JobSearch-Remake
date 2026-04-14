package kg.attractor.jobsearch_remake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ContactInfoDto {
    private Integer id;
    private Integer resumeId;
    private Integer typeId;
    private String value;
}
