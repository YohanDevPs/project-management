package com.api.management.model;

import com.api.management.enums.PaymentStatus;
import com.api.management.enums.PricingType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tab_sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pricing_type", nullable = false)
    private PricingType pricing_type;
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
    @Column(nullable = false)
    private Date moment;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unit_price;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "weight_price")
    private BigDecimal weight_price;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "total_price")
    private BigDecimal total_price;
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PricingType getPricing_type() {
        return pricing_type;
    }

    public void setPricing_type(PricingType pricing_type) {
        this.pricing_type = pricing_type;
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

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getWeight_price() {
        return weight_price;
    }

    public void setWeight_price(BigDecimal weight_price) {
        this.weight_price = weight_price;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
