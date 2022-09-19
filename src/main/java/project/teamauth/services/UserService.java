package project.teamauth.services;

import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.RegistrationDto;
import project.teamauth.exceptions.UserAlreadyExistException;
import project.teamauth.exceptions.UserNotValidException;
import project.teamauth.mappers.UserMapper;
import project.teamauth.model.User;
import project.teamauth.repositories.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository repository;

    public User register(RegistrationDto registrationDto) {
        User user = userMapper.fromRegistrationDto(registrationDto);
        Optional<User> byLogin = repository.findByLogin(user.getLogin());
        Optional<User> byEmail = repository.findByEmail(user.getEmail());
        if (!Strings.hasLength(user.getEmail()) || !Strings.hasLength(user.getLogin())){
            throw UserNotValidException.instance();
        }
        if (byLogin.isPresent() || byEmail.isPresent()){
            throw UserAlreadyExistException.instance();
        }
        return repository.save(user);
    }
}