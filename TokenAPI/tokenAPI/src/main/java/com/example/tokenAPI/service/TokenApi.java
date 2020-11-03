package com.example.tokenAPI.service;

import com.example.tokenAPI.model.Payments;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.web3j.contracts.eip20.generated.ERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;

@Component
public class TokenApi {
    public static String pk;

    /*Blockchain connection*/
    Web3j web3 = Web3j.build(new HttpService("http://localhost:8545"));

    public List<ERC20.TransferEventResponse> makeTransaction(Payments payment) {
        System.out.println("amount:" + payment.getAmount() + " accountfrom:" + payment.getAccountfrom() + " accountto:" + payment.getAccountto());
        /*try {
            File file = ResourceUtils.getFile("classpath:pk.txt");
            Scanner myReader = new Scanner(file);
            pk = myReader.nextLine();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred when private key is read.");
            e.printStackTrace();
        }*/

        Credentials credentials = Credentials.create(payment.getPk());
        String contractAddress = "0x5CB0E78448e0f374B688D6B9D79e10e51eEc897B";
        ERC20 ERC20Token = ERC20.load(contractAddress, web3, credentials, new DefaultGasProvider());

        String accountto = payment.getAccountto();
        String accountfrom = payment.getAccountfrom();
        String amount = payment.getAmount().toString();
        TransactionReceipt receipt = null;
        List<ERC20.TransferEventResponse> events = null ;
        try {
            /*Check current balance*/
            BigInteger balance1_a = ERC20Token.balanceOf(accountfrom).send();
            BigInteger balance_a = ERC20Token.balanceOf(accountto).send();
            System.out.println("Check current balance from: " + balance1_a);
            System.out.println("Check current balance to: " + balance_a);

            receipt = ERC20Token.transfer(accountto, new BigInteger(amount)).send();
            events = ERC20Token.getTransferEvents(receipt);

            /*Check balance after transaction*/
            BigInteger balance_b = ERC20Token.balanceOf(accountto).send();
            System.out.println("balance from after transaction: " + balance_a);
            System.out.println("balance to after transaction: " + balance_b);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }
}
