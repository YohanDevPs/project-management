package com.api.management.service.sale;

import com.api.management.model.Sale;
import com.api.management.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Set;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;


    @Override
    public Sale findById(Long id) {
        return saleRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Set<Sale> findSetByCustomerId(Long id) {
        return null;
    }

    @Override
    public Sale create(Sale sale) {
        return saleRepository.save(sale);
    }

    @Override
    public Sale update(Sale sale) {
        var entity = saleRepository.findById(sale.getId())
                .orElseThrow(NoSuchElementException::new);

        entity.setPricing_type(sale.getPricing_type());
        entity.setPaymentStatus(sale.getPaymentStatus());
        entity.setMoment(sale.getMoment());
        entity.setUnit_price(sale.getUnit_price());
        entity.setQuantity(sale.getQuantity());
        entity.setWeight_price(sale.getWeight_price());
        entity.setWeight(sale.getWeight());
        entity.setTotal_price(sale.getTotal_price());
        entity.setProducts(sale.getProducts());
        entity.setCustomer(sale.getCustomer());

        return entity;
    }

    @Override
    public void deleteById(Long id) {
        saleRepository.deleteById(id);
    }
}
