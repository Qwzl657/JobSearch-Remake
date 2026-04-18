package kg.attractor.jobsearch_remake.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Surname cannot be blank")
    private String surname;

    @Min(value = 14, message = "Age must be at least 14")
    @Max(value = 100, message = "Age must be less than 100")
    private Integer age;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email format is invalid")
    private String email;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Phone number format is invalid")
    private String phoneNumber;

    private String avatar;

    @NotBlank(message = "Account type cannot be blank")
    @Pattern(regexp = "^(APPLICANT|EMPLOYER)$", message = "Account type must be APPLICANT or EMPLOYER")
    private String accountType;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 4, message = "Password must be at least 4 characters")
    private String password;
}