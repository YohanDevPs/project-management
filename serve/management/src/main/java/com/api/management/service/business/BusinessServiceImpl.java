package com.api.management.service.business;

import com.api.management.model.Business;
import com.api.management.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    @Override
    public Business findBusinessById(Long BusinessId) {
        return businessRepository.findById(BusinessId).get();
    }

    @Override
    public Set<Business> findBusinessSetByCustomerId(Long customerId) {
        return businessRepository.findByCustomerId(customerId);
    }

    @Override
    public void saveBusiness(Business business) {
        businessRepository.save(business);
    }

    @Override
    public Business updateCustomer(Long BusinessId) {
        return null;
    }

    @Override
    public void deleteBusinessById(Long businessId) {
        businessRepository.deleteById(businessId);
    }
}
