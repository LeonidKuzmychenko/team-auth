package project.teamauth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.repositories.SubscribeRepository;

@Service
@AllArgsConstructor
public class SubscribeService {

    private final SubscribeRepository repository;

}
