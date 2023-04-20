package com.api.management.service.sale;

import com.api.management.dto.SaleDTO;

import java.util.List;

public interface SaleService {

    SaleDTO findById(Long id);
    List<SaleDTO> findListByCustomerId(Long id);
    SaleDTO create(Long customerId, SaleDTO dto);
    SaleDTO update(SaleDTO sale);
    void deleteById(Long id);
}
