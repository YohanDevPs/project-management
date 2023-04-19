package com.api.management.controller;

import com.api.management.dto.AddressDTO;
import com.api.management.dto.CustomerDTO;
import com.api.management.service.adress.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {


    @Autowired
    private AddressService service;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping("/customer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDTO> getCustomersByUserId(@PathVariable("id") Long id) {
        return service.findAddressSetByCustomerId(id);
    }

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO create(@PathVariable("userId") Long customerId, @RequestBody AddressDTO dto) {
        return service.create(customerId, dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO update(@RequestBody AddressDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

}
