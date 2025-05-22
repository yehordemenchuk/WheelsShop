package org.wheelsshop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "users", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String hashPassword;

    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("car-user-reference")
    private List<Car> likedCars;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("order-user-reference")
    private List<Order> orders;
}
