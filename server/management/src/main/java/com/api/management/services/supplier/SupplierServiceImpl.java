package com.api.management.services.supplier;

import com.api.management.controllers.SupplierController;
import com.api.management.dto.SupplierDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Supplier;
import com.api.management.repositorys.SupplierRepository;
import com.api.management.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.api.management.util.constants.ErrorMessageConstants.SUPPLIER_NOT_FOUND_MSG;
import static com.api.management.util.constants.ErrorMessageConstants.USER_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static com.api.management.util.mapper.UtilModelMapper.parseSetObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Set<SupplierDTO> findSuppliersByUserId(Long id) {
        var suppliers = supplierRepository.findByUserId(id);
        var suppliersDTOs = parseSetObjects(suppliers, SupplierDTO.class);

        for (SupplierDTO dto: suppliersDTOs) {
            dto.add(linkTo(methodOn(SupplierController.class)
                    .findById(dto.getId()))
                    .withSelfRel());
        }

        return suppliersDTOs;
    }

    @Override
    public SupplierDTO findById(Long id) {
        var supplierEntity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SUPPLIER_NOT_FOUND_MSG, id)));

        var supplierDTO = parseObject(supplierEntity, SupplierDTO.class);
        supplierDTO.add(linkTo(methodOn(SupplierController.class).findById(supplierDTO.getId())).withSelfRel());
        return supplierDTO;
    }

    @Override
    public SupplierDTO create(Long userId, SupplierDTO dto) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        var supplierEntity = parseObject(dto, Supplier.class);
        supplierEntity.setUser(userEntity);
        supplierRepository.save(supplierEntity);
        var supplierDTO = parseObject(supplierEntity, SupplierDTO.class);
        supplierDTO.add(linkTo(methodOn(SupplierController.class).findById(supplierDTO.getId())).withSelfRel());
        return supplierDTO;
    }

    @Override
    public SupplierDTO update(SupplierDTO dto) {
        var supplierEntity = supplierRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SUPPLIER_NOT_FOUND_MSG, dto.getId())));

        supplierEntity.setName(dto.getName());
        supplierEntity.setPhone(dto.getPhone());
        supplierEntity.setComplement(dto.getComplement());

        var supplierDTO = parseObject(supplierRepository.save(supplierEntity), SupplierDTO.class);
        supplierDTO.add(linkTo(methodOn(SupplierController.class).findById(supplierDTO.getId())).withSelfRel());
        return supplierDTO;
    }

    @Override
    public void delete(Long id) {
        var entity = supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(SUPPLIER_NOT_FOUND_MSG, id)));

        supplierRepository.delete(entity);
    }
}
