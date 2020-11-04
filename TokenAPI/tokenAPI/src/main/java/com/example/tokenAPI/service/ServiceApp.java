package com.example.tokenAPI.service;

import com.example.tokenAPI.dao.PaymentsRepository;
import com.example.tokenAPI.model.Events;
import com.example.tokenAPI.model.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.web3j.contracts.eip20.generated.ERC20;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class ServiceApp {
    @Autowired
    private PaymentsRepository paymentRepository;

    /**
     * Store event in the database
     *
     * @param payment
     * @param events
     * @return payment event saved if operation successful
     */
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

    /**
     * Find all events from database
     *
     * @return List of all events
     */
    public List<Events> getEvents() {
        return paymentRepository.findAll();
    }

    /**
     * Get events gived the paymentId
     *
     * @param paymentId
     * @return
     */
    public List<Events> getBypaymentId(String paymentId) {
        List<Events> allRecords = getEvents();
        List<Events> result = new ArrayList<Events>();
        Iterator<Events> i = allRecords.iterator();
        while (i.hasNext()) {
            Events item = i.next();
            String i_id = item.getPaymentId().toString();
            if (Objects.equals(paymentId, i_id)) {
                result.add(item);
            }
        }
        return result;
    }
}