package com.api.management.model;

import com.api.management.enums.BusinessType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tab_business")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotBlank(message = "businessType cannot be null" )
    @Column(name = "business_type", nullable = false)
    private BusinessType businessType;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "suplier_id")
    private Supplier supplier;
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public Business() {
    }

    public Business(BusinessType businessType, Customer customer, Supplier supplier) {
        this.businessType = businessType;
        if(businessType == BusinessType.SALE) {
            this.customer = customer;
            this.supplier = null;
        } else {
            this.supplier = supplier;
            this.customer = null;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusinessType getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessType businessType) {
        this.businessType = businessType;
    }
    
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
