package project.teamauth.configuration.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import project.teamauth.models.Role;
import project.teamauth.models.Subscription;
import project.teamauth.models.User;
import project.teamauth.repositories.RoleRepository;
import project.teamauth.repositories.SubscribeRepository;
import project.teamauth.repositories.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service("initDb")
public class InitDb {

    public static int rnd(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    @Autowired
    public void init(UserRepository userRepository, RoleRepository roleRepository, SubscribeRepository subscribeRepository, PasswordEncoder passwordEncoder) {
        Role role = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        Optional<User> testUser = userRepository.findByLogin("testuser");
        if (testUser.isEmpty()) {
            User user = new User();
            user.setEmail("test@gmail.com");
            user.setLogin("testuser");
            user.setPassword(passwordEncoder.encode("testuser"));
            user.setRoles(Collections.singleton(role));
            User saveUser = userRepository.save(user);

            saveSubscription(subscribeRepository, saveUser, new Subscription("Instagram", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Facebook", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Telegram", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Google", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test1", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test2", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test3", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test4", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test5", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test6", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test7", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test8", rnd(1000, 9999)));
            saveSubscription(subscribeRepository, saveUser, new Subscription("Test9", rnd(1000, 9999)));
        }
    }

    public void saveSubscription(SubscribeRepository subscribeRepository, User saveUser, Subscription subscription) {
        saveUser.getSubscriptions().add(subscription);
        subscription.setUser(saveUser);
        subscribeRepository.save(subscription);
    }

//    @Autowired
//    public void initSubscriptions(UserRepository userRepository, SubscribeRepository subscribeRepository) {
//        Optional<User> testUser = userRepository.findByLogin("testuser");
//        if (testUser.isPresent()) {
//            User user = testUser.get();
//            Subscription subscription = new Subscription("Instagram", rnd(1000,9999));
//            subscription.setUser(user);
//            user.setSubscriptions(Collections.singleton(subscription));
//            subscribeRepository.save(subscription);
//        }
//    }
}