package com.api.management.services.supplier;

import com.api.management.dto.SupplierDTO;

import java.util.Set;

public interface SupplierService {

    SupplierDTO findById(Long orderId);
    Set<SupplierDTO> findSuppliersByUserId(Long userId);
    SupplierDTO create(Long userId, SupplierDTO dto);
    SupplierDTO update(SupplierDTO dto);
    void delete(Long orderId);
}
