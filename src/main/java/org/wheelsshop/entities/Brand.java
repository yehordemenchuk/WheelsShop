package org.wheelsshop.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "brands", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String brandName;

    private String emblem;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference("car-brand-reference")
    private List<Car> relatedCars;
}
