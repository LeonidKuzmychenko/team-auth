package project.teamauth.services;

import io.jsonwebtoken.lang.Strings;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.RegistrationRequestDto;
import project.teamauth.exceptions.PasswordsNotMatchException;
import project.teamauth.exceptions.UserAlreadyExistException;
import project.teamauth.exceptions.UserNotValidException;
import project.teamauth.mappers.UserMapper;
import project.teamauth.models.User;
import project.teamauth.repositories.UserRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User register(RegistrationRequestDto dto) {
        if (!dto.getPassword1().equals(dto.getPassword2())) {
            throw PasswordsNotMatchException.instance();
        }
        User user = userMapper.fromRegistrationDto(dto);
        Optional<User> byLogin = userRepository.findByLogin(user.getLogin());
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (!Strings.hasLength(user.getEmail()) || !Strings.hasLength(user.getLogin())) {
            throw UserNotValidException.instance();
        }
        if (byLogin.isPresent() || byEmail.isPresent()) {
            throw UserAlreadyExistException.instance();
        }
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        Optional<User> byLogin = userRepository.findByLogin(login);
        if (byLogin.isPresent()) {
            return byLogin.get();
        }
        throw new RuntimeException();
    }
}