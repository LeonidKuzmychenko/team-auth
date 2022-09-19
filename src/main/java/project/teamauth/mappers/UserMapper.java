package project.teamauth.mappers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.RegistrationDto;
import project.teamauth.exceptions.PasswordsNotMatchException;
import project.teamauth.model.Role;
import project.teamauth.model.User;
import project.teamauth.services.RoleService;

import java.util.Collections;

@Service
public class UserMapper {

    private final PasswordEncoder passwordEncoder;
    private final Role role;

    public UserMapper(PasswordEncoder passwordEncoder, RoleService roleService) {
        this.passwordEncoder = passwordEncoder;
        this.role = roleService.findByName("ROLE_USER");
    }

    public User fromRegistrationDto(RegistrationDto registrationDto) {
        if (!registrationDto.getPassword1().equals(registrationDto.getPassword2())) {
            throw PasswordsNotMatchException.instance();
        }
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setLogin(registrationDto.getLogin());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword1()));
        user.setRoles(Collections.singleton(role));
        return user;
    }
}