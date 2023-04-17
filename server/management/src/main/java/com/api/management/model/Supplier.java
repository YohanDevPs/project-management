package com.api.management.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tab_supplier")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone", nullable = false)
    private String phone;
    @Column(name = "complement", nullable = false)
    private String complement;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Supplier() {
    }

    public Supplier(String name, String phone, String complement, User user) {
        this.name = name;
        this.phone = phone;
        this.complement = complement;
        this.user = user;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
