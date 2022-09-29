package project.teamauth.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.teamauth.configuration.cache.CacheManager;
import project.teamauth.dtos.*;
import project.teamauth.models.Partner;
import project.teamauth.repositories.PartnerRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PartnerService {

    private final PartnerRepository repository;
    private final CacheManager cacheManager;

    public PartnerRegistrationResponseDto register(PartnerRegistrationRequestDto partnerRegistrationRequestDto) {
        Partner partner = new Partner();
        partner.setToken(UUID.randomUUID().toString());
        partner.setName(partnerRegistrationRequestDto.getName());
        Partner savedPartner = repository.save(partner);
        return new PartnerRegistrationResponseDto(savedPartner.getToken());
    }

    public PartnerLoginResponseDto login(PartnerLoginRequestDto partnerLoginRequestDto) {
        Optional<Partner> partner = repository.findByToken(partnerLoginRequestDto.getToken());
        if (partner.isPresent()) {
            String key = UUID.randomUUID().toString();
            PartnerLoginCacheDto partnerLoginCacheDto = new PartnerLoginCacheDto();
            partnerLoginCacheDto.setPartner(partner.get());
            partnerLoginCacheDto.setPartnerId(partnerLoginRequestDto.getId());
            cacheManager.add(key, partnerLoginCacheDto);
            return new PartnerLoginResponseDto(key);
        }
        throw new RuntimeException();
    }
}