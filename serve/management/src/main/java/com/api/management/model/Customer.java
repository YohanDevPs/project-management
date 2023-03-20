package com.api.management.model;

import com.api.management.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private long contact;
    private Date lastPurchase;
    private PaymentStatus paymenteStatus;
    private Boolean potencialResale;

    public Customer(Long id, String name, long contact, Date lastPurchase, PaymentStatus paymenteStatus, Boolean potencialResale) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.lastPurchase = lastPurchase;
        this.paymenteStatus = paymenteStatus;
        this.potencialResale = potencialResale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public Date getLastPurchase() {
        return lastPurchase;
    }

    public void setLastPurchase(Date lastPurchase) {
        this.lastPurchase = lastPurchase;
    }

    public PaymentStatus getPaymenteStatus() {
        return paymenteStatus;
    }

    public void setPaymenteStatus(PaymentStatus paymenteStatus) {
        this.paymenteStatus = paymenteStatus;
    }

    public Boolean getPotencialResale() {
        return potencialResale;
    }

    public void setPotencialResale(Boolean potencialResale) {
        this.potencialResale = potencialResale;
    }
}
