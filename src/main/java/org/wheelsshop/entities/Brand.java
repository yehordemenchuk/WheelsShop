package org.wheelsshop.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Brand {
    @Id
    @GeneratedValue(generator = "brand_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "brand_seq", sequenceName = "brand_sequence", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private String brandName;

    private String emblem;

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Car> relatedCars;
}
