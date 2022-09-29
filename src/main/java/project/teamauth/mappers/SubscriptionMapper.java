package project.teamauth.mappers;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.teamauth.dtos.SubscriptionResponseDto;
import project.teamauth.models.Subscription;

@Service
@AllArgsConstructor
public class SubscriptionMapper {

    private final ModelMapper modelMapper;

    public SubscriptionResponseDto map(Subscription subscription) {
        return modelMapper.map(subscription, SubscriptionResponseDto.class);
    }
}
