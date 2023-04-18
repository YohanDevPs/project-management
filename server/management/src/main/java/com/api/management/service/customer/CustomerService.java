package com.api.management.service.customer;

import com.api.management.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO findCustomerById(Long id);
    List<CustomerDTO> findCustomerSetByUserId(Long userId);
    CustomerDTO saveCustomer(Long idUser, CustomerDTO dto);
    CustomerDTO update(CustomerDTO dto);
    void deleteByCustomerId(Long customerId);
}
