package project.teamauth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Passwords not match")
public class PasswordsNotMatchException extends RuntimeException {

    private PasswordsNotMatchException(String message) {
        super(message);
    }

    public static PasswordsNotMatchException instance() {
        return new PasswordsNotMatchException("Passwords not match");
    }
}