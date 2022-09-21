package project.teamauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.teamauth.models.Subscription;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscription, Long> {

}
