package com.api.management.services.address;

import com.api.management.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    AddressDTO findById(Long id);
    List<AddressDTO> findAddressSetByCustomerId(Long userId);
    AddressDTO create(Long idCustomer, AddressDTO dto);
    AddressDTO update(AddressDTO dto);
    void delete(Long customerId);
}
