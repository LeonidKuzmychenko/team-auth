package project.teamauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerLoginRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    private String id;
}
