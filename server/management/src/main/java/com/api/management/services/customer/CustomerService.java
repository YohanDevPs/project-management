package com.api.management.services.customer;

import com.api.management.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO findCustomerById(Long id);
    List<CustomerDTO> findCustomersByUserId(Long userId);
    CustomerDTO create(Long idUser, CustomerDTO dto);
    CustomerDTO update(CustomerDTO dto);
    void deleteByCustomerId(Long customerId);
}
