package com.api.management.mocks;

import com.api.management.dto.AddressDTO;
import com.api.management.models.Address;

import java.util.ArrayList;
import java.util.List;

public class MockAddress {

    public Address mockEntity() {
        return mockEntity(0);
    }

    public AddressDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Address> mockEntityList() {
        List<Address> addresses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            addresses.add(mockEntity(i));
        }
        return addresses;
    }

    public List<AddressDTO> mockDTOList() {
        List<AddressDTO> addresses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            addresses.add(mockDTO(i));
        }
        return addresses;
    }

    public Address mockEntity(Integer number) {
        Address address = new Address();

        address.setId(number.longValue());
        address.setStreet("Street Fairview" + number);
        address.setNumber(number);
        address.setComplement("Complement"+number);
        address.setCity("Montglomery" + number);
        address.setState("Alabama" + number);
        address.setZipcode("43-12331" + number);

        return address;
    }

    public AddressDTO mockDTO(Integer number) {
        AddressDTO dto = new AddressDTO();

        dto.setId(number.longValue());
        dto.setStreet("Street Fairview" + number);
        dto.setNumber(number);
        dto.setComplement("Complement"+number);
        dto.setCity("Montglomery" + number);
        dto.setState("Alabama" + number);
        dto.setZipcode("43-12331" + number);

        return dto;
    }
}
