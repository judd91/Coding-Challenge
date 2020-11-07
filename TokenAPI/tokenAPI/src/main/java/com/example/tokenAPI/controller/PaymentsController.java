package com.example.tokenAPI.controller;

import com.example.tokenAPI.dao.PaymentsRepository;
import com.example.tokenAPI.model.Payments;
import com.example.tokenAPI.model.Events;
import com.example.tokenAPI.service.ServiceApp;
import com.example.tokenAPI.service.TokenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import org.web3j.contracts.eip20.generated.ERC20;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping(path="/payment")
public class PaymentsController {
    @Autowired
    private ServiceApp paymentService;

    @Autowired
    private TokenApi apiService;

    @Autowired
    PaymentsRepository paymentRepo;

    public static String pk;

    /**
     * Make a transaction gived the account details and amount
     * The event is saved in a Database
     *
     * @param payment
     * @return Operation done if everything is successful
     * @throws Exception
     */
    @PostMapping
    public String addPayment(@RequestBody Payments payment) throws Exception {

        /* Make a transaction*/
        List<ERC20.TransferEventResponse> receipt = apiService.makeTransaction(payment);

        String result;
        /* Save payment event */
        if (receipt != null){
            paymentService.addPayment(payment, receipt);
            result = "Operation done =)";
        } else {
            result = "Operation not executed =(";
        }
        return result ;
    }

    /**
     * Get all stored events from database
     * @return Iterable events
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Events> getAllPayments() {
        return paymentService.getEvents();
    }

    /**
     * Get event from database by id
     *
     * @param payId
     * @return Event
     */
    @GetMapping(path="/all/{id}")
    public Optional<Events> getById(@PathVariable(value = "id") Integer payId) {
        return paymentRepo.findById(payId);
    }

    /**
     * Get event/s from database by paymentId
     *
     * @param paymentId
     * @return Iterable events
     */
    @GetMapping("/get")
    public @ResponseBody Iterable<Events> getByPayId(@RequestParam String paymentId) {
        return paymentService.getBypaymentId(paymentId);
    }

    /*@GetMapping("/deploy")
    public void deployContract() throws IOException {
        MyERC20Token mytoken;
         System.out.println(resource);
    }*/

}
