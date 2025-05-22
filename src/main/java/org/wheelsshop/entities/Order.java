package org.wheelsshop.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "generator", sequenceName = "orders", allocationSize = 1)
    private Long id;

    private Status status;

    private Integer count;

    private BigDecimal amount;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonBackReference("order-user-reference")
    private User user;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id", nullable=false)
    @JsonBackReference("order-car-reference")
    private Car car;
}
