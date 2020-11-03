package com.example.tokenAPI.service;
import com.example.tokenAPI.dao.PaymentsRepository;
import com.example.tokenAPI.model.Events;
import com.example.tokenAPI.model.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.contracts.eip20.generated.ERC20;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import java.util.List;

@Component
public class ServiceApp {
    @Autowired // This means to get the bean called bookRepository
    private PaymentsRepository paymentRepository;

    public String addPayment(Payments payment, List<ERC20.TransferEventResponse> events) {
        Events paymentEvent = new Events();
        paymentEvent.setId(payment.getId());
        paymentEvent.setPaymentId(UUID.randomUUID().toString());

        events.forEach(event ->
        {
            System.out.println("from: " + event._from + ", to: " + event._to + ", value: " + event._value);
            LocalDateTime now = LocalDateTime.now();
            paymentEvent.setDate(now);
            paymentEvent.setAmount(event._value.intValue());
            paymentEvent.setFromAccount(event._from);
            paymentEvent.setToAccount(event._to);
            //paymentEvent.setSuccess(false);
            paymentRepository.save(paymentEvent);
        });


        return "payment event saved";
    }

    public List<Events> getEvents() {
        return paymentRepository.findAll();
    }

}