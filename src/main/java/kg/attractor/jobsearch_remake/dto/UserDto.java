package kg.attractor.jobsearch_remake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "{validation.name.blank}")
    private String name;

    @NotBlank(message = "{validation.surname.blank}")
    private String surname;

    @Min(value = 14, message = "{validation.age.min}")
    @Max(value = 100, message = "{validation.age.max}")
    private Integer age;

    private String email;

    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "{validation.phone.invalid}")
    private String phoneNumber;

    private String avatar;
    private String accountType;
}