package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {

    @NotBlank(message = "{validation.name.blank}")
    @Pattern(regexp = "^[^\\d]+$", message = "{validation.name.no.digits}")
    private String name;

    @NotBlank(message = "{validation.surname.blank}")
    @Pattern(regexp = "^[^\\d]+$", message = "{validation.surname.no.digits}")
    private String surname;

    @Min(value = 14, message = "{validation.age.min}")
    @Max(value = 100, message = "{validation.age.max}")
    private Integer age;

    @NotBlank(message = "{validation.email.blank}")
    @Email(message = "{validation.email.invalid}")
    private String email;

    @NotBlank(message = "{validation.phone.blank}")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "{validation.phone.invalid}")
    private String phoneNumber;

    @NotBlank(message = "{validation.accountType.blank}")
    @Pattern(regexp = "^(APPLICANT|EMPLOYER)$", message = "{validation.accountType.invalid}")
    private String accountType;

    @NotBlank(message = "{validation.password.blank}")
    @Size(min = 8, message = "{validation.password.size}")
    private String password;
}