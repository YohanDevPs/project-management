package com.api.management.service.supplier;

import com.api.management.model.Supplier;
import com.api.management.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier findSupplierById(Long id) {
        return supplierRepository.findById(id).get();
    }

    @Override
    public Set<Supplier> findSupplierSetByUserId(Long id) {
        return supplierRepository.findByUserId(id);
    }

    @Override
    public void saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Long orderId) {
        return null;
    }

    @Override
    public void deleteSupplierById(Long id) {
        supplierRepository.deleteById(id);
    }
}
