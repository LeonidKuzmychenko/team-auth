package project.teamauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "User already exists")
public class UserAlreadyExistException extends RuntimeException {

    private UserAlreadyExistException(String message) {
        super(message);
    }

    public static UserAlreadyExistException instance(){
        return new UserAlreadyExistException("User already exists");
    }
}