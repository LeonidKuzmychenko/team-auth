package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.teamauth.dtos.RegistrationRequestDto;
import project.teamauth.models.User;
import project.teamauth.services.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public User registration(@Valid @RequestBody RegistrationRequestDto registrationRequestDto) {
        return userService.register(registrationRequestDto);
    }
}
