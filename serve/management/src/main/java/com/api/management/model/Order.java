package com.api.management.model;

import com.api.management.enums.PricingType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tab_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pricing_type", nullable = false)
    private PricingType pricing_type;
    @Column(name = "unit_price", nullable = false)
    private BigDecimal unit_price;
    @Column
    private Date moment;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "weight_price")
    private BigDecimal weight_price;
    @Column(name = "weight")
    private BigDecimal weight;
    @Column(name = "total_price")
    private BigDecimal total_price;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "business_id")
    private Business business;

    public Order() {
    }

    public Order(PricingType pricing_type,
                 BigDecimal unit_price,
                 Date moment,
                 Integer quantity,
                 BigDecimal weight_price,
                 BigDecimal weight,
                 BigDecimal total_price,
                 Set<Product> products,
                 Business business) {
        this.pricing_type = pricing_type;
        this.unit_price = unit_price;
        this.moment = moment;
        this.quantity = quantity;
        this.weight_price = weight_price;
        this.weight = weight;
        this.total_price = total_price;
        this.products = products;
        this.business = business;
    }

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

    public BigDecimal getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(BigDecimal unit_price) {
        this.unit_price = unit_price;
    }

    public Date getMoment() {
        return moment;
    }

    public void setMoment(Date moment) {
        this.moment = moment;
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

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}
