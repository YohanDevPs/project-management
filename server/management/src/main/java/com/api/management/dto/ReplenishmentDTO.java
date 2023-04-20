package com.api.management.dto;

import com.api.management.enums.DeliveryStatus;
import com.api.management.enums.PaymentStatus;

import java.math.BigDecimal;
import java.util.Date;

public class ReplenishmentDTO {

    private Long id;
    private DeliveryStatus deliveryStatus;
    private PaymentStatus paymentStatus;
    private Date moment;
    private BigDecimal total_price;

    public ReplenishmentDTO() {
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

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
}
