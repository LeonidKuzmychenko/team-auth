//package project.teamauth.services;
//
//import org.springframework.stereotype.Service;
//import project.teamauth.model.User;
//import project.teamauth.repositories.UserRepository;
//
//import java.util.Optional;
//
//@Service
//public class UserService {
//
//    private final UserRepository repository;
//
//    public UserService(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    public User register(User user) {
//        if (user == null) {
//            throw new RuntimeException();
//        }
//        if (user.getLogin() == null || user.getEmail() == null || user.getPassword() == null) {
//            throw new RuntimeException();
//        }
//        return repository.save(user);
//    }
//
//    public User login(Long id) {
//        Optional<User> byId = repository.findById(id);
//        if (byId.isPresent()) {
//            return byId.get();
//        } else {
//            throw new RuntimeException();
//        }
//    }
//}