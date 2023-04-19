package com.api.management.controller;

import com.api.management.dto.CustomerDTO;
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
    public Set<CustomerDTO> getCustomersByUserId(@PathVariable("userId") Long userId) {
        return customerService.findCustomerSetByUserId(userId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        customerService.deleteByCustomerId(id);
    }

    @PostMapping("/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@PathVariable("userId") Long userId, @RequestBody CustomerDTO dto) {
        return customerService.create(userId, dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO update(@RequestBody CustomerDTO dto) {
        return customerService.update(dto);
    }
}
