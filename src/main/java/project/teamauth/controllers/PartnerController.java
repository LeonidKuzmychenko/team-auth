package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.teamauth.dtos.PartnerLoginRequestDto;
import project.teamauth.dtos.PartnerLoginResponseDto;
import project.teamauth.dtos.PartnerRegistrationRequestDto;
import project.teamauth.dtos.PartnerRegistrationResponseDto;
import project.teamauth.services.PartnerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/partner")
@AllArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PostMapping("/registration")
    public PartnerRegistrationResponseDto partnerRegistration(@Valid @RequestBody PartnerRegistrationRequestDto partnerRegistrationRequestDto) {
        return partnerService.register(partnerRegistrationRequestDto);
    }

    @PostMapping("/login")
    public PartnerLoginResponseDto registration(@Valid @RequestBody PartnerLoginRequestDto partnerLoginRequestDto) {
        return partnerService.login(partnerLoginRequestDto);
    }
}