package com.example.tokenAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Payments {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String accountto;
    private Integer amount;
    private String accountfrom;
    private String pk;

    public Payments() {
        super();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountto() {
        return accountto;
    }

    public void setAccountto(String accountto) {
        this.accountto = accountto;
    }

    public String getAccountfrom() {
        return accountfrom;
    }

    public void setAccountfrom(String accountfrom) {
        this.accountfrom = accountfrom;
    }

    public String getPk() {
        return pk;
    }

    public void setPk(String pk) {
        this.pk = pk;
    }

    public Payments(String accountto, String accountfrom, String pk, int amount) {
        super();
        this.accountfrom = accountfrom;
        this.accountto = accountto;
        this.pk = pk;
        this.amount = amount;
    }
}
