package com.api.management.service.address;

import com.api.management.dto.AddressDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.mapper.UtilModelMapper;
import com.api.management.models.Address;
import com.api.management.repositorys.AddressRepository;
import com.api.management.repositorys.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.api.management.mapper.UtilModelMapper.parseObject;
import static com.api.management.mapper.UtilModelMapper.parseSetObjects;

@Service
public class AddressServiceImpl implements AddressService {

    public static final String ADDRESS_NOT_FOUND = "Address not found, id: ";
    @Autowired
    private AddressRepository repository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddressDTO findById(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_NOT_FOUND + id));
        return UtilModelMapper.parseObject(entity, AddressDTO.class);
    }

    @Override
    public Set<AddressDTO> findAddressSetByCustomerId(Long customerId) {
        return parseSetObjects(repository.findByCustomerId(customerId), AddressDTO.class);
    }

    @Override
    public AddressDTO create(Long customerId, AddressDTO dto) {
        var customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found, id: " + customerId));

        var entity = parseObject(dto, Address.class);
        entity.setCustomer(customer);

        return parseObject(repository.save(entity), AddressDTO.class);
    }

    @Override
    public AddressDTO update(AddressDTO dto) {
        var entity = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_NOT_FOUND + dto.getId()));

        entity.setStreet(dto.getStreet());
        entity.setNumber(dto.getNumber());
        entity.setComplement(dto.getComplement());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setZipcode(dto.getZipcode());

        return parseObject(repository.save(entity), AddressDTO.class);
    }

    @Override
    public void delete(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ADDRESS_NOT_FOUND + id));

        repository.delete(entity);
    }
}
