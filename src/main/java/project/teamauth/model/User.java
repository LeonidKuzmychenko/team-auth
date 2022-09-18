//package project.teamauth.model;
//
//import lombok.*;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.OneToMany;
//import java.util.HashSet;
//import java.util.Set;
//
//@Getter
//@Setter
//@ToString
//@AllArgsConstructor
//@RequiredArgsConstructor
//@Entity("user")
//public class User {
//
//    @Id
//    private Long id;
//
//    @Column
//    private String email;
//
//    @Column
//    private String login;
//
//    @Column
//    private String password;
//
//    @Column
//    @OneToMany
//    @ToString.Exclude
//    private Set<Subscribe> subscribes = new HashSet<>();
//}