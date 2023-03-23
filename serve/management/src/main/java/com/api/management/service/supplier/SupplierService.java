package com.api.management.service.supplier;

import com.api.management.model.Supplier;

import java.util.Set;

public interface SupplierService {
    Supplier findSupplierById(Long orderId);
    Set<Supplier> findSupplierSetByUserId(Long userId);
    void saveSupplier(Supplier order);
    Supplier updateSupplier(Long orderId);
    void deleteSupplierById(Long orderId);
}
