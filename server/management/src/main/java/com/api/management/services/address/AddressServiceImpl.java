package com.api.management.services.address;

import com.api.management.controllers.AddressController;
import com.api.management.dto.AddressDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Address;
import com.api.management.repositorys.AddressRepository;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.util.constants.ErrorMessageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.constants.ErrorMessageConstants.ADDRESS_NOT_FOUND_MSG;
import static com.api.management.util.constants.ErrorMessageConstants.CUSTOMER_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddressDTO findById(Long id) {
        var addressEntity = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ADDRESS_NOT_FOUND_MSG, id)));

        var addressDTO = parseObject(addressEntity, AddressDTO.class);
        addressDTO.add(linkTo(methodOn(AddressController.class).findById(id)).withSelfRel());
        return addressDTO;
    }

    @Override
    public List<AddressDTO> findAddressSetByCustomerId(Long id) {
        var listAddressDTO = parseListObjects(addressRepository.findByCustomerId(id), AddressDTO.class);

        for (AddressDTO dto :listAddressDTO) {
            dto.add(linkTo(methodOn(AddressController.class)
                    .findById(dto.getId()))
                    .withSelfRel());
        }
        return listAddressDTO;
    }

    @Override
    public AddressDTO create(Long customerId, AddressDTO dto) {
        var customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, customerId)));

        var addressEntity = parseObject(dto, Address.class);
        addressEntity.setCustomer(customerEntity);

        var addressDTO = parseObject(addressRepository.save(addressEntity), AddressDTO.class);
        addressDTO.add(linkTo(methodOn(AddressController.class).findById(addressDTO.getId())).withSelfRel());
        return addressDTO;
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        var addressDTO = addressRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND_MSG, dto.getId())));

        addressDTO.setStreet(dto.getStreet());
        addressDTO.setNumber(dto.getNumber());
        addressDTO.setComplement(dto.getComplement());
        addressDTO.setCity(dto.getCity());
        addressDTO.setState(dto.getState());
        addressDTO.setZipcode(dto.getZipcode());

        var dtoPersisted = parseObject(addressRepository.save(addressDTO), AddressDTO.class);
        dtoPersisted.add(linkTo(methodOn(AddressController.class).findById(dtoPersisted.getId())).withSelfRel());
        return dtoPersisted;
    }

    @Override
    public void delete(Long addressId) {
        var address = addressRepository.findById(addressId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ErrorMessageConstants.ADDRESS_NOT_FOUND_MSG, addressId)));

        addressRepository.delete(address);
    }
}
