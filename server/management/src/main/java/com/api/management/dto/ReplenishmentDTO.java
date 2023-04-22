package com.api.management.dto;

import com.api.management.enums.DeliveryStatus;
import com.api.management.enums.PaymentStatus;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.util.Date;

public class ReplenishmentDTO extends RepresentationModel<ReplenishmentDTO> {

    private Long id;
    private DeliveryStatus deliveryStatus;
    private PaymentStatus paymentStatus;
    private Date moment;
    private BigDecimal totalPrice;

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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReplenishmentDTO that)) return false;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getDeliveryStatus() != that.getDeliveryStatus()) return false;
        if (getPaymentStatus() != that.getPaymentStatus()) return false;
        if (getMoment() != null ? !getMoment().equals(that.getMoment()) : that.getMoment() != null) return false;
        return getTotalPrice() != null ? getTotalPrice().equals(that.getTotalPrice()) : that.getTotalPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDeliveryStatus() != null ? getDeliveryStatus().hashCode() : 0);
        result = 31 * result + (getPaymentStatus() != null ? getPaymentStatus().hashCode() : 0);
        result = 31 * result + (getMoment() != null ? getMoment().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        return result;
    }
}
