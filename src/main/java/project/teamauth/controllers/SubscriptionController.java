package project.teamauth.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import project.teamauth.dtos.SubscriptionRequestDto;
import project.teamauth.dtos.SubscriptionResponseDto;
import project.teamauth.mappers.SubscriptionMapper;
import project.teamauth.models.Subscription;
import project.teamauth.models.User;
import project.teamauth.repositories.UserRepository;
import project.teamauth.services.SubscribeService;
import project.teamauth.services.UserService;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class SubscriptionController {

    private final UserService userService;
    private final SubscribeService subscribeService;
    private final SubscriptionMapper subscriptionMapper;

    @GetMapping(value = "/subscriptions")
    public Set<SubscriptionResponseDto> subscriptions(Authentication authentication) {
        return userService.findByLogin(authentication.getName())
                .getSubscriptions()
                .stream()
                .map(subscriptionMapper::map)
                .collect(Collectors.toSet());
    }

    @PostMapping(value = "/save")
    public SubscriptionResponseDto save(Authentication authentication, @Valid @RequestBody SubscriptionRequestDto request) {
        Subscription subscription = subscribeService
                .addSubscription(authentication.getName(), request.getKey(), request.getName());
        return subscriptionMapper.map(subscription);
    }
}