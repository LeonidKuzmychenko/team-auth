package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.teamauth.dtos.RegistrationDto;
import project.teamauth.model.User;
import project.teamauth.services.UserService;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/registration")
    public User registration(@RequestBody RegistrationDto registrationDto) {
        return userService.register(registrationDto);
    }
}
