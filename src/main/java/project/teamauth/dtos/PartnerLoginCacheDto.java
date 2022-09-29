package project.teamauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.teamauth.models.Partner;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartnerLoginCacheDto {
    private String partnerId;
    private Partner partner;
}