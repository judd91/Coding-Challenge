package com.example.tokenAPI.controller;

import com.example.tokenAPI.model.Payments;
import com.example.tokenAPI.model.Events;
import com.example.tokenAPI.service.ServiceApp;
import com.example.tokenAPI.service.TokenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.util.List;

@RestController
@RequestMapping(path="/payment")
public class PaymentsController {
    @Autowired
    private ServiceApp paymentService;

    @Autowired
    private TokenApi apiService;

    public static String pk;

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

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Events> getAllPayments() {
        return paymentService.getEvents();
    }

}
