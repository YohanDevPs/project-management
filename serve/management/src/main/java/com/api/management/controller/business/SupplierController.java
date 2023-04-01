package com.api.management.controller.business;

import com.api.management.model.Supplier;
import com.api.management.service.supplier.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/allByUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Supplier> getSupplierByUserId(@PathVariable("id") Long id) {
        return supplierService.findSupplierSetByUserId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Supplier getSupplierById(@PathVariable("id") Long id) {
        return supplierService.findSupplierById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSupplierById(@PathVariable("id") Long id) {
        supplierService.deleteSupplierById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveSupplier(@RequestBody Supplier supplier) {
        supplierService.saveSupplier(supplier);
    }
}
