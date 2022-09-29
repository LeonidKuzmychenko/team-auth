package project.teamauth.configuration.db;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.teamauth.configuration.cache.CacheManager;
import project.teamauth.dtos.PartnerLoginCacheDto;
import project.teamauth.models.Partner;
import project.teamauth.models.Role;
import project.teamauth.models.User;
import project.teamauth.repositories.PartnerRepository;
import project.teamauth.repositories.RoleRepository;
import project.teamauth.repositories.UserRepository;
import project.teamauth.services.SubscribeService;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class InitDb {

    private final CacheManager cacheManager;
    private final PartnerRepository partnerRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscribeService subscribeService;
    private final PasswordEncoder passwordEncoder;

    //    public static int rnd(int min, int max) {
//        max -= min;
//        return (int) (Math.random() * ++max) + min;
//    }
//
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Role role = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        Optional<User> testUser = userRepository.findByLogin("testuser");
        if (testUser.isEmpty()) {
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setLogin("testuser");
            user.setPassword(passwordEncoder.encode("testuser"));
            user.setRoles(Collections.singleton(role));
            User saveUser = userRepository.save(user);

            Partner partner = new Partner();
            partner.setName("Instagram");
            partner.setToken(UUID.randomUUID().toString());
            Partner savedPartner = partnerRepository.save(partner);

            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);
//            addSubscription(subscribeService, cacheManager, savedPartner);


//            saveSubscription(subscribeRepository, saveUser, new Subscription("Instagram", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Facebook", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Telegram", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Google", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test1", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test2", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test3", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test4", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test5", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test6", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test7", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test8", rnd(1000, 9999)));
//            saveSubscription(subscribeRepository, saveUser, new Subscription("Test9", rnd(1000, 9999)));
        }
    }

//    public void saveSubscription(SubscribeRepository subscribeRepository, User saveUser, Subscription subscription) {
//        saveUser.getSubscriptions().add(subscription);
//        subscription.setUser(saveUser);
//        subscribeRepository.save(subscription);
//    }

    public void addSubscription(SubscribeService subscribeService, CacheManager cacheManager, Partner partner) {
        String key = UUID.randomUUID().toString();
        PartnerLoginCacheDto partnerLoginCacheDto = new PartnerLoginCacheDto();
        partnerLoginCacheDto.setPartner(partner);
        partnerLoginCacheDto.setPartnerId("123456");
        cacheManager.add(key, partnerLoginCacheDto);
        subscribeService.addSubscription("testuser", key, "Instagram");
    }
}