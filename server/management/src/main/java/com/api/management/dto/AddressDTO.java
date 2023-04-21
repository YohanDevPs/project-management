package com.api.management.dto;


import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class AddressDTO extends RepresentationModel<AddressDTO> {

    private Long id;
    private String street;
    private Integer number;
    private String complement;
    private String city;
    private String state;
    private String zipcode;

    public AddressDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO that)) return false;

        if (!Objects.equals(id, that.id)) return false;
        if (getStreet() != null ? !getStreet().equals(that.getStreet()) : that.getStreet() != null) return false;
        if (getNumber() != null ? !getNumber().equals(that.getNumber()) : that.getNumber() != null) return false;
        if (getComplement() != null ? !getComplement().equals(that.getComplement()) : that.getComplement() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null) return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null) return false;
        return getZipcode() != null ? getZipcode().equals(that.getZipcode()) : that.getZipcode() == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getNumber() != null ? getNumber().hashCode() : 0);
        result = 31 * result + (getComplement() != null ? getComplement().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getZipcode() != null ? getZipcode().hashCode() : 0);
        return result;
    }
}
