package com.api.management.services.customer;

import com.api.management.dto.CustomerDTO;

import java.util.Set;

public interface CustomerService {

    CustomerDTO findCustomerById(Long id);
    Set<CustomerDTO> findCustomerSetByUserId(Long userId);
    CustomerDTO create(Long idUser, CustomerDTO dto);
    CustomerDTO update(CustomerDTO dto);
    void deleteByCustomerId(Long customerId);
}
