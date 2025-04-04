package org.wheelsshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name="generator", sequenceName = "cars", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    @NonNull
    private String carName;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal price;

    @ElementCollection
    private List<String> carImages = new ArrayList<>();

    @NonNull
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
