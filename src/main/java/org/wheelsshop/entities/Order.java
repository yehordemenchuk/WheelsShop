package org.wheelsshop.entities;

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
    @GeneratedValue(generator = "order_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "order_seq", sequenceName = "order_sequence", allocationSize = 1)
    @Setter(AccessLevel.NONE)
    private Long id;

    private Status status;

    private Integer count;

    private BigDecimal amount;

    private Date createdAt;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    @JoinColumn(name="car_id", referencedColumnName = "id", nullable=false)
    private Car car;
}
