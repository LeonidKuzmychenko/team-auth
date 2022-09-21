package project.teamauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequestDto {

    @NotBlank
    @Email(message = "field should have email format")
    private String email;

    @NotBlank
    @Size(min = 2, message = "user login should have at least 2 characters")
    private String login;

    @NotBlank
    @Size(min = 6, message = "user password should have at least 6 characters")
    private String password1;

    @NotBlank
    @Size(min = 6, message = "user password should have at least 6 characters")
    private String password2;
}