package project.teamauth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.configuration.cache.CacheManager;
import project.teamauth.dtos.PartnerLoginCacheDto;
import project.teamauth.models.Subscription;
import project.teamauth.models.User;
import project.teamauth.repositories.SubscribeRepository;

@Service
@AllArgsConstructor
public class SubscribeService {

    private final UserService userService;
    private final SubscribeRepository subscribeRepository;
    private final CacheManager cacheManager;

    public Subscription addSubscription(String login, String key, String name) {
        User user = userService.findByLogin(login);
        PartnerLoginCacheDto cache = cacheManager.get(key);
        Subscription subscription = new Subscription(cache.getPartner(), name, 4444);
        user.getSubscriptions().add(subscription);
        subscription.setUser(user);
        return subscribeRepository.save(subscription);
    }
}
