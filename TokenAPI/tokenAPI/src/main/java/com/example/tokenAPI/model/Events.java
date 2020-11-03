package com.example.tokenAPI.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Events {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String paymentId;
    private String fromAccount;
    private String toAccount;
    private Integer amount;
    private LocalDateTime date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccount() {
        return toAccount;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    private Boolean success;


    public Events() {
        super();
    }

    public Events(String paymentId, Boolean success, String fromAccount, String toAccount, int amount, LocalDateTime date) {
        super();
        this.date = date;
        this.toAccount = toAccount;
        this.paymentId = paymentId;
        this.fromAccount = fromAccount;
        this.amount = amount;
        this.success = success;
    }
}
