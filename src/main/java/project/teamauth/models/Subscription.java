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

    @Column
    private String name;

    @Column
    private Integer key;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Subscription(String name, Integer key) {
        this.name = name;
        this.key = key;
    }
}
