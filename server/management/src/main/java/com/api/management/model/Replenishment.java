package com.api.management.model;

import com.api.management.enums.DeliveryStatus;
import com.api.management.enums.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tab_replenishment")
public class Replenishment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "delivery_status", nullable = false)
    private DeliveryStatus deliveryStatus;
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @Column(nullable = false)
    private Date moment;
    @Column(name = "total_price")
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "replenishment", cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Replenishment() {
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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
