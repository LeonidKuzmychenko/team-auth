package project.teamauth.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionResponseDto {

    private Long id;
    private String name;
    private Integer key;
}
