package project.teamauth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.repositories.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository repository;

}
