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

    @NotBlank(message = "Имя не может быть пустым")
    private String name;

    @NotBlank(message = "Фамилия не может быть пустой")
    private String surname;

    @Min(value = 14, message = "Возраст не менее 14 лет")
    @Max(value = 100, message = "Возраст не более 100 лет")
    private Integer age;

    @NotBlank(message = "Email не может быть пустым")
    @Email(message = "Неверный формат email")
    private String email;

    @NotBlank(message = "Телефон не может быть пустым")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Неверный формат телефона")
    private String phoneNumber;

    @NotBlank(message = "Тип аккаунта не может быть пустым")
    @Pattern(regexp = "^(APPLICANT|EMPLOYER)$", message = "Тип аккаунта: APPLICANT или EMPLOYER")
    private String accountType;

    @NotBlank(message = "Пароль не может быть пустым")
    @Size(min = 8, message = "Пароль не менее 8 символов")
    private String password;
}