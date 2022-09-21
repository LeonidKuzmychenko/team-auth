package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.teamauth.models.Subscription;
import project.teamauth.models.User;
import project.teamauth.repositories.UserRepository;

import java.util.Optional;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class SubscriptionController {

    private final UserRepository userRepository;

    @GetMapping(value = "/subscriptions")
    public Set<Subscription> subscriptions(Authentication authentication) {
        String login = authentication.getName();
        Optional<User> byLogin = userRepository.findByLogin(login);
        if (byLogin.isPresent()) {
            User user = byLogin.get();
            return user.getSubscriptions();
        }
        throw new RuntimeException();
    }

}
