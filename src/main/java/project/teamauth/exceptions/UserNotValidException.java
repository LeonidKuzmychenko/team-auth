package project.teamauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User not valid")
public class UserNotValidException extends RuntimeException {

    private UserNotValidException(String message) {
        super(message);
    }

    public static UserNotValidException instance(){
        return new UserNotValidException("User not valid");
    }
}