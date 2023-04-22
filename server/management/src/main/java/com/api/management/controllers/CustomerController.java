package com.api.management.controllers;

import com.api.management.dto.CustomerDTO;
import com.api.management.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.api.management.util.constants.UtilMediaType.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/allByUser/{userId}",
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public Set<CustomerDTO> findAllByUserId(@PathVariable("userId") Long userId) {
        return customerService.findCustomerSetByUserId(userId);
    }

    @GetMapping(value = "/{id}",
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO findById(@PathVariable("id") Long id) {
        return customerService.findCustomerById(id);
    }

    @PostMapping(value = "/{userId}",
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@PathVariable("userId") Long userId, @RequestBody CustomerDTO dto) {
        return customerService.create(userId, dto);
    }

    @PutMapping(consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO update(@RequestBody CustomerDTO dto) {
        return customerService.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        customerService.deleteByCustomerId(id);
    }
}
