package project.teamauth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.teamauth.models.Partner;

import java.util.Optional;

@Repository
public interface PartnerRepository extends JpaRepository<Partner, Long> {

    Optional<Partner> findByToken(String token);
}
