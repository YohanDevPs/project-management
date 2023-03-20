package com.api.management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String neiborhood;
    @NotBlank
    private String street;
    @NotBlank
    private int number;
    private String complement;

    public Adress() {
    }

    public Adress(String neiborhood, String street, int number, String complement) {
        this.neiborhood = neiborhood;
        this.street = street;
        this.number = number;
        this.complement = complement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNeiborhood() {
        return neiborhood;
    }

    public void setNeiborhood(String neiborhood) {
        this.neiborhood = neiborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
