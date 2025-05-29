package org.wheelsshop.request;

import org.wheelsshop.entities.Status;

import java.math.BigDecimal;
import java.util.Date;

public record OrderRequest(Status status,
                           Integer count,
                           BigDecimal amount,
                           Date createdAt,
                           Long userId,
                           Long carId) {
}
