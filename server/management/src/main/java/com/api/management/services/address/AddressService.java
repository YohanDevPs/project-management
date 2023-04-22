package com.api.management.services.address;

import com.api.management.dto.AddressDTO;

import java.util.Set;

public interface AddressService {
    AddressDTO findById(Long id);
    Set<AddressDTO> findAddressSetByCustomerId(Long userId);
    AddressDTO create(Long idCustomer, AddressDTO dto);
    AddressDTO update(AddressDTO dto);
    void delete(Long customerId);
}
