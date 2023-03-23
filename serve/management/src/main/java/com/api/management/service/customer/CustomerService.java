package com.api.management.service.customer;

import com.api.management.model.Customer;

import java.util.Set;

public interface CustomerService {

    Customer findCustomerById(Long id);
    Set<Customer> findCustomerSetByUserId(Long userId);
    void saveCustomer(Customer customer);
    Customer updateCustomer(Long customerId);
    void deleteCustomerById(Long customerId);
}
