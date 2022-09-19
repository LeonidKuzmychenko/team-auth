package project.teamauth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.model.Role;
import project.teamauth.repositories.RoleRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public Role findByName(String name) {
        return repository.findByName(name);
    }

    @PostConstruct
    public void init(){
        List<Role> all = repository.findAll();
        if (all.size() == 0){
            repository.save(new Role("ROLE_USER"));
        }
    }
}
