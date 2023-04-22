package com.api.management.services.sale;

import com.api.management.controllers.SaleController;
import com.api.management.dto.SaleDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Sale;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.repositorys.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.constants.ErrorMessageConstants.CUSTOMER_NOT_FOUND_MSG;
import static com.api.management.util.constants.ErrorMessageConstants.SALE_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class SaleServiceImpl implements SaleService{

    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public SaleDTO findById(Long id) {
        var saleEntity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SALE_NOT_FOUND_MSG, id)));
        
        var saleDTO = parseObject(saleEntity, SaleDTO.class);
        saleDTO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
        return saleDTO;
    }

    @Override
    public List<SaleDTO> findSalesByCustomerId(Long id) {
        var customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, id)));

        var saleDTOs = parseListObjects(saleRepository.findByCustomerId(customerEntity.getId()), SaleDTO.class);

        for (SaleDTO dto: saleDTOs) {
            dto.add(linkTo(methodOn(SaleController.class)
                    .findById(dto.getId()))
                    .withSelfRel());
        }

        return saleDTOs;
    }

    @Override
    public SaleDTO create(Long customerId, SaleDTO dto) {
        var customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, customerId)));

        var saleEntity = parseObject(dto, Sale.class);
        saleEntity.setCustomer(customerEntity);

        var salePersistedDTO = parseObject(saleRepository.save(saleEntity), SaleDTO.class);
        salePersistedDTO.add(linkTo(methodOn(SaleController.class).findById(salePersistedDTO.getId())).withSelfRel());
        return salePersistedDTO;
    }

    @Override
    public SaleDTO update(SaleDTO dto) {
        var saleEntity = saleRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SALE_NOT_FOUND_MSG, dto.getId())));


        saleEntity.setDeliveryStatus(dto.getDeliveryStatus());
        saleEntity.setPaymentStatus(dto.getPaymentStatus());
        saleEntity.setMoment(dto.getMoment());
        saleEntity.setTotalPrice(dto.getTotalPrice());

        var salePersistedDTO = parseObject(saleRepository.save(saleEntity), SaleDTO.class);
        salePersistedDTO.add(linkTo(methodOn(SaleController.class).findById(salePersistedDTO.getId())).withSelfRel());
        return salePersistedDTO;

    }

    @Override
    public void deleteById(Long id) {
        var entity = saleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SALE_NOT_FOUND_MSG, id)));

        saleRepository.delete(entity);
    }
}
