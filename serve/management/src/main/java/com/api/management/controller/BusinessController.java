package com.api.management.controller;

import com.api.management.model.Business;
import com.api.management.service.business.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @GetMapping("/customers/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Business> getBusinessByCustomerId(@PathVariable("customerId") Long customerId) {
        return businessService.findBusinessSetByCustomerId(customerId);
    }

    @GetMapping("/{businessId}")
    @ResponseStatus(HttpStatus.OK)
    public Business getBusinessById(@PathVariable("businessId") Long businessId) {
        return businessService.findBusinessById(businessId);
    }

    @DeleteMapping("/{businessId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBusinessById(@PathVariable("businessId") Long businessId) {
        businessService.deleteBusinessById(businessId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveBusiness(@RequestBody Business business) {
        businessService.saveBusiness(business);
    }
}
