package com.api.management.service.sale;

import com.api.management.model.Sale;

import java.util.Set;

public interface SaleService {

    Sale findById(Long id);
    Set<Sale> findSetByCustomerId(Long id);
    Sale create(Sale sale);
    Sale update(Sale sale);
    void deleteById(Long id);
}
