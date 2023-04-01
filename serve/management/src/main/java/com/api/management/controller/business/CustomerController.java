package com.api.management.controller.business;

import com.api.management.model.Customer;
import com.api.management.service.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/allByUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Customer> getCustomersByUserId(@PathVariable("userId") Long userId) {
        return customerService.findCustomerSetByUserId(userId);
    }

    @GetMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomerById(@PathVariable("customerId") Long customerId) {
        return customerService.findCustomerById(customerId);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable("customerId") Long customerId) {
        customerService.deleteCustomerById(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveCustomer(@RequestBody Customer Customer) {
        customerService.saveCustomer(Customer);
    }
}
