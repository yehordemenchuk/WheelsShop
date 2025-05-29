package org.wheelsshop.entities;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Car {
    @Id
    @GeneratedValue(generator = "car_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="car_seq", sequenceName = "car_sequence", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String carName;

    private String description;

    private BigDecimal price;

    @ElementCollection
    private List<String> carImages = new ArrayList<>();

    private Integer count;

    @ManyToOne
    @JoinColumn(name="brand_id", nullable=false)
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "car", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true)
    List<Order> orders = new ArrayList<>();
}
