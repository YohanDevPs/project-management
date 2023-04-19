package com.api.management.service.supplier;

import com.api.management.dto.SupplierDTO;

import java.util.Set;

public interface SupplierService {

    SupplierDTO findById(Long orderId);
    Set<SupplierDTO> findSupplierSetByUserId(Long userId);
    SupplierDTO create(Long userId, SupplierDTO dto);
    SupplierDTO update(SupplierDTO dto);
    void delete(Long orderId);
}
