package com.api.management.service.replenishment;

import com.api.management.dto.ReplenishmentDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Replenishment;
import com.api.management.repositorys.ReplenishmentRepository;
import com.api.management.repositorys.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;

@Service
public class ReplenishmentServiceImpl implements ReplenishmentService{

    @Autowired
    private ReplenishmentRepository repository;
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public ReplenishmentDTO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reposição nao encontrada"));

        return parseObject(entity, ReplenishmentDTO.class);
    }

    @Override
    public List<ReplenishmentDTO> findReplenishmentListBySupplierId(Long customerId) {
        var supplierEntity = supplierRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        return parseListObjects(repository.findBySupplierId(supplierEntity.getId()), ReplenishmentDTO.class);
    }

    @Override
    public ReplenishmentDTO create(Long supplierId, ReplenishmentDTO dto) {
        var supplierEntity = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Supplier not found, id: ", supplierId)));

        var replenishment = parseObject(dto, Replenishment.class);
        replenishment.setSupplier(supplierEntity);
        repository.save(replenishment);
        return parseObject(replenishment, ReplenishmentDTO.class);
    }

    @Override
    public ReplenishmentDTO update(ReplenishmentDTO dto) {
        var entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Replenishment not found, id: ", dto.getId())));

        entity.setDeliveryStatus(dto.getDeliveryStatus());
        entity.setPaymentStatus(dto.getPaymentStatus());
        entity.setMoment(dto.getMoment());
        entity.setTotalPrice(dto.getTotalPrice());

        return parseObject(repository.save(entity), ReplenishmentDTO.class);
    }

    @Override
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Replenishment not found, id: ", id)));

        repository.delete(entity);
    }
}
