package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.wheelsshop.dto.OrderDto;
import org.wheelsshop.entities.Order;
import org.wheelsshop.entities.User;
import org.wheelsshop.mapper.OrderMapper;
import org.wheelsshop.repository.jpa.OrderJpaRepository;
import org.wheelsshop.repository.redis.OrderRedisRepository;
import org.wheelsshop.request.OrderRequest;

import java.lang.reflect.InvocationTargetException;

@Service
public class OrderService extends AbstractService<OrderDto, Order, OrderRequest> {
    private final MailSenderService mailSenderService;

    @Autowired
    public OrderService(OrderJpaRepository orderJpaRepository,
                        OrderRedisRepository orderRedisRepository,
                        OrderMapper orderMapper,
                        MailSenderService mailSenderService) {
        super(orderJpaRepository, orderRedisRepository, Order.class, orderMapper);

        this.mailSenderService = mailSenderService;
    }

    @Override
    public void save(OrderRequest orderRequest) throws InvocationTargetException,
            NoSuchMethodException, IllegalAccessException {

        super.save(orderRequest);

        User user = getMapper().fromRequest(orderRequest).getUser();

        mailSenderService.sendOrderEmail(orderRequest, user.getEmail());
    }
}
