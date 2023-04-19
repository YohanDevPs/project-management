package com.api.management.service.adress;

import com.api.management.dto.AddressDTO;
import com.api.management.exception.ResourceNotFoundException;
import com.api.management.mapper.UtilModelMapper;
import com.api.management.model.Address;
import com.api.management.repository.AddressRepository;
import com.api.management.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.mapper.UtilModelMapper.parseObject;

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
    public List<AddressDTO> findAddressSetByCustomerId(Long customerId) {
        return parseListObjects(repository.findByCustomerId(customerId), AddressDTO.class);
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
