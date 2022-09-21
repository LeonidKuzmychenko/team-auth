package project.teamauth.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.RegistrationRequestDto;
import project.teamauth.models.Role;
import project.teamauth.models.User;

import java.util.Collections;

@Service
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final Role role;

    public User fromRegistrationDto(RegistrationRequestDto dto) {
        Converter<String, String> encodeConverter = context -> passwordEncoder.encode(context.getSource());
        modelMapper.typeMap(RegistrationRequestDto.class, User.class).addMappings(
                mapper -> mapper.using(encodeConverter).map(RegistrationRequestDto::getPassword1, User::setPassword));
        User user = modelMapper.map(dto, User.class);
        user.setRoles(Collections.singleton(role));
        return user;
    }
}