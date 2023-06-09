package com.api.management.controllers;

import com.api.management.dto.AddressDTO;
import com.api.management.services.address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.management.util.constants.UtilMediaType.*;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping(value = "/{id}",  
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @ResponseStatus(HttpStatus.OK)
    public AddressDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @GetMapping(value = "/customer/{id}", 
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @ResponseStatus(HttpStatus.OK)
    public List<AddressDTO> findAllByUserId(@PathVariable("id") Long id) {
        return service.findAddressSetByCustomerId(id);
    }

    @PostMapping(value = "/{customerId}" , 
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
    @ResponseStatus(HttpStatus.CREATED)
    public AddressDTO create(@PathVariable("customerId") Long customerId, @RequestBody AddressDTO dto) {
        return service.create(customerId, dto);
    }

    @PutMapping( consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML })
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
