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
            Subscription subscription = new Subscription("Instagram", 1234);
            subscription.setUser(user);
            user.setSubscriptions(Collections.singleton(subscription));
            userRepository.save(user);
            subscribeRepository.save(subscription);
        }
    }

//    @Autowired
//    public void initSubscriptions(UserRepository userRepository, SubscribeRepository subscribeRepository) {
//        Optional<User> testUser = userRepository.findByLogin("testuser");
//        if (testUser.isPresent()) {
//            User user = testUser.get();
//            Subscription subscription = new Subscription("Instagram", 1234);
//            subscription.setUser(user);
//            user.setSubscriptions(Collections.singleton(subscription));
//            subscribeRepository.save(subscription);
//        }
//    }
}