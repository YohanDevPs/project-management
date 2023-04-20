package com.api.management.service.supplier;

import com.api.management.dto.SupplierDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.exceptions.UserNotFoundException;
import com.api.management.models.Supplier;
import com.api.management.repositorys.SupplierRepository;
import com.api.management.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.api.management.mapper.UtilModelMapper.parseObject;
import static com.api.management.mapper.UtilModelMapper.parseSetObjects;

@Service
public class SupplierServiceImpl implements SupplierService {

    public static final String CUSTOMER_NOT_FOUND = "Customer com id: %d não encontrado";
    public static final String USER_NOT_FOUND = "Usuário com id: %d não encontrado";
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<SupplierDTO> findSupplierSetByUserId(Long id) {
        var entities = supplierRepository.findByUserId(id);
        return parseSetObjects(entities, SupplierDTO.class);
    }

    @Override
    public SupplierDTO findById(Long id) {
        var entity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Supplier not found"));

        return parseObject(entity, SupplierDTO.class);
    }

    @Override
    public SupplierDTO create(Long userId, SupplierDTO dto) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, userId)));

        var supplierEntity = parseObject(dto, Supplier.class);
        supplierEntity.setUser(userEntity);
        supplierRepository.save(supplierEntity);
        return parseObject(supplierEntity, SupplierDTO.class);
    }

    @Override
    public SupplierDTO update(SupplierDTO dto) {
        var entity = supplierRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND, dto.getId())));

        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setComplement(dto.getComplement());

        return parseObject(supplierRepository.save(entity), SupplierDTO.class);
    }

    @Override
    public void delete(Long id) {
        var entity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND, id)));

        supplierRepository.delete(entity);
    }
}
