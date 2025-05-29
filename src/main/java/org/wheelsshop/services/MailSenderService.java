package org.wheelsshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.wheelsshop.request.OrderRequest;

import java.math.BigDecimal;

@Service
public class MailSenderService {
    private final MailSender mailSender;

    private static final String EMAIL_ADDRESS = "demenchuk1210m@gmail.com";
    private static final String EMAIL_SUBJECT = "We got an order from you";
    private static final BigDecimal TAX = BigDecimal.valueOf(0.05);

    @Autowired
    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    private double getCostWithTax(OrderRequest orderRequest) {
        BigDecimal amount = orderRequest.amount();

        return amount.add(amount.multiply(TAX)).doubleValue();
    }

    private String getMessageAboutCar(OrderRequest orderRequest) {
        return String.format("Hello. We got an order from you" +
                "\nThe finall sum is %.2f. Enter to our shop to finish your order",
                getCostWithTax(orderRequest));
    }

    private void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        message.setFrom(EMAIL_ADDRESS);

        mailSender.send(message);
    }

    public void sendOrderEmail(OrderRequest orderRequest, String to) {
        sendSimpleEmail(to, EMAIL_SUBJECT, getMessageAboutCar(orderRequest));
    }
}