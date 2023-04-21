package com.api.management.controllers;

import com.api.management.dto.SupplierDTO;
import com.api.management.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static com.api.management.util.constants.UtilMediaType.*;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping(value = "/user/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public Set<SupplierDTO> findByUserId(@PathVariable("id") Long id) {
        return supplierService.findSupplierSetByUserId(id);
    }

    @GetMapping(value = "/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public SupplierDTO findById(@PathVariable("id") Long id) {
        return supplierService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        supplierService.delete(id);
    }

    @PostMapping(value = "/user/{id}",
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.CREATED)
    public SupplierDTO create(@PathVariable("id") Long id, @RequestBody SupplierDTO dto) {
        return supplierService.create(id, dto);
    }

    @PutMapping(consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public SupplierDTO update(@RequestBody SupplierDTO dto) {
        return supplierService.update(dto);
    }
}
