package project.teamauth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Entity(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column
    private Integer key;

    @Column(name = "partner_user_id")
    private String partnerUserId;

    @JsonIgnore
    @ManyToOne
    private User user;

    @ManyToOne
    private Partner partner;

    public Subscription(Partner partner, String name, Integer key) {
        this.partner = partner;
        this.name = name;
        this.key = key;
    }
}
