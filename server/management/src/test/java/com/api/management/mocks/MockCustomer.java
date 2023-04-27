package com.api.management.mocks;

import com.api.management.dto.CustomerDTO;
import com.api.management.models.Customer;

import java.util.ArrayList;
import java.util.List;

public class MockCustomer {

    public Customer mockEntity() {
        return mockEntity(0);
    }

    public CustomerDTO mockDTO() {
        return mockDTO(0);
    }

    public List<Customer> mockEntityList() {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customers.add(mockEntity(i));
        }
        return customers;
    }

    public List<CustomerDTO> mockDTOList() {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            customerDTOS.add(mockDTO(i));
        }
        return customerDTOS;
    }

    public Customer mockEntity(Integer number) {
        Customer customer = new Customer();

        customer.setId(number.longValue());
        customer.setName("Yohan" + number);
        customer.setEmail("yohan@email.com"+number);
        customer.setPhone("7188374819"+number);

        return customer;
    }

    public CustomerDTO mockDTO(Integer number) {
        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setId(number.longValue());
        customerDTO.setName("Yohan" + number);
        customerDTO.setEmail("yohan@email.com"+number);
        customerDTO.setPhone("7188374819"+number);

        return customerDTO;
    }
}
