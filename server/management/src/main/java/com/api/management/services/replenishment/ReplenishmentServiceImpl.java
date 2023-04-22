package com.api.management.services.replenishment;

import com.api.management.controllers.ReplenishmentController;
import com.api.management.dto.ReplenishmentDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Replenishment;
import com.api.management.repositorys.ReplenishmentRepository;
import com.api.management.repositorys.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.constants.ErrorMessageConstants.REPLENISHMENT_NOT_FOUND_MSG;
import static com.api.management.util.constants.ErrorMessageConstants.SUPPLIER_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ReplenishmentServiceImpl implements ReplenishmentService{

    @Autowired
    private ReplenishmentRepository replenishmentRepository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ReplenishmentDTO findById(Long id) {
        var replenishmentEntity = replenishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(REPLENISHMENT_NOT_FOUND_MSG, id)));

        var replenishmentDTO = parseObject(replenishmentEntity, ReplenishmentDTO.class);
        replenishmentDTO.add(linkTo(methodOn(ReplenishmentController.class).findById(id)).withSelfRel());
        return replenishmentDTO;
    }

    @Override
    public List<ReplenishmentDTO> findReplenishmentsBySupplierId(Long id) {
        var supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SUPPLIER_NOT_FOUND_MSG, id)));

        var replenishmentDTOS = parseListObjects(
                replenishmentRepository.findBySupplierId(supplierEntity.getId()), ReplenishmentDTO.class
        );

        for (ReplenishmentDTO dto: replenishmentDTOS) {
            dto.add(linkTo(methodOn(ReplenishmentController.class)
                    .findById(id))
                    .withSelfRel());
        }

        return replenishmentDTOS;
    }

    @Override
    public ReplenishmentDTO create(Long supplierId, ReplenishmentDTO dto) {
        var supplierEntity = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SUPPLIER_NOT_FOUND_MSG, supplierId)));

        var replenishmentEntity = parseObject(dto, Replenishment.class);
        replenishmentEntity.setSupplier(supplierEntity);

        var replenishmentPersistedDTO = parseObject(replenishmentRepository.save(replenishmentEntity), ReplenishmentDTO.class);
        replenishmentPersistedDTO.add(linkTo(methodOn(ReplenishmentController.class)
                .findById(replenishmentPersistedDTO.getId())).withSelfRel());

        return replenishmentPersistedDTO;
    }

    @Override
    public ReplenishmentDTO update(ReplenishmentDTO dto) {
        var replenishmentEntity = replenishmentRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(REPLENISHMENT_NOT_FOUND_MSG, dto.getId())));

        replenishmentEntity.setDeliveryStatus(dto.getDeliveryStatus());
        replenishmentEntity.setPaymentStatus(dto.getPaymentStatus());
        replenishmentEntity.setMoment(dto.getMoment());
        replenishmentEntity.setTotalPrice(dto.getTotalPrice());

        var replenishmentPersistedDTO = parseObject(replenishmentRepository.save(replenishmentEntity), ReplenishmentDTO.class);
        replenishmentPersistedDTO.add(linkTo(methodOn(ReplenishmentController.class)
                .findById(replenishmentPersistedDTO.getId()))
                .withSelfRel());

        return replenishmentPersistedDTO;
    }

    @Override
    public void delete(Long id) {
        var entity = replenishmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(REPLENISHMENT_NOT_FOUND_MSG, id)));

        replenishmentRepository.delete(entity);
    }
}
