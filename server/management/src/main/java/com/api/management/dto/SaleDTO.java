package com.api.management.dto;

import com.api.management.enums.DeliveryStatus;
import com.api.management.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class SaleDTO {

    private Long id;
    private DeliveryStatus deliveryStatus;
    private PaymentStatus paymentStatus;
    private Date moment;
    private BigDecimal totalPrice;

    public SaleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SaleDTO saleDTO)) return false;

        return getId() != null ? getId().equals(saleDTO.getId()) : saleDTO.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
