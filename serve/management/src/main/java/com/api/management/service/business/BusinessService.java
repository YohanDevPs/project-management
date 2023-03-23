package com.api.management.service.business;

import com.api.management.model.Business;

import java.util.Set;

public interface BusinessService {

    Business findBusinessById(Long BusinessId);

    Set<Business> findBusinessSetByCustomerId(Long BusinessId);

    void saveBusiness(Business business);
    Business updateCustomer(Long BusinessId);
    void deleteBusinessById(Long businessId);
}
