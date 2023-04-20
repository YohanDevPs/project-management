package com.api.management.service.sale;

import com.api.management.dto.SaleDTO;
import com.api.management.model.Sale;

import java.util.List;
import java.util.Set;

public interface SaleService {

    SaleDTO findById(Long id);
    List<SaleDTO> findListByCustomerId(Long id);
    SaleDTO create(Long customerId, SaleDTO dto);
    SaleDTO update(SaleDTO sale);
    void deleteById(Long id);
}
