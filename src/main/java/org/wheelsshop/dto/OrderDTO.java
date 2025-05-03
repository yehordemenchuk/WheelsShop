package org.wheelsshop.dto;

import lombok.Data;
import org.wheelsshop.entities.Status;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDTO {
    private final Status status;

    private final Integer count;

    private final BigDecimal amount;

    private final Date createdAt;
}
