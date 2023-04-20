package com.api.management.service.sale;

import com.api.management.dto.SaleDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.mapper.UtilModelMapper;
import com.api.management.models.Sale;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.repositorys.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.mapper.UtilModelMapper.parseObject;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public SaleDTO findById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));
        
        return parseObject(entity, SaleDTO.class);
    }

    @Override
    public List<SaleDTO> findListByCustomerId(Long id) {
        var customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        var saleList= saleRepository.findByCustomerId(customerEntity.getId());
        return UtilModelMapper.parseListObjects(saleList, SaleDTO.class);
    }

    @Override
    public SaleDTO create(Long customerId, SaleDTO dto) {
        var entity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        var saleEntity = parseObject(dto, Sale.class);
        saleEntity.setCustomer(entity);

        return parseObject(saleRepository.save(saleEntity), SaleDTO.class);
    }

    @Override
    public SaleDTO update(SaleDTO dto) {
        var entity = saleRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));


        entity.setDeliveryStatus(dto.getDeliveryStatus());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setMoment(dto.getMoment());
        entity.setTotalPrice(dto.getTotalPrice());

        return parseObject(saleRepository.save(entity), SaleDTO.class);
    }

    @Override
    public void deleteById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sale not found"));

        saleRepository.delete(entity);
    }
}
