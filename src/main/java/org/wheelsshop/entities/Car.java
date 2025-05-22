package org.wheelsshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="generator", sequenceName = "cars", allocationSize = 1)
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
    @JsonBackReference("car-brand-reference")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference("car-user-reference")
    private User user;
}
