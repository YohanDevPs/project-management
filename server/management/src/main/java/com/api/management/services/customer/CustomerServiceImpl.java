package com.api.management.services.customer;

import com.api.management.controllers.CustomerController;
import com.api.management.dto.CustomerDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.models.Customer;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.util.constants.ErrorMessageConstants.CUSTOMER_NOT_FOUND_MSG;
import static com.api.management.util.constants.ErrorMessageConstants.USER_NOT_FOUND_MSG;
import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerDTO findCustomerById(Long id) {
        var customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, id)));

        var customerDTO = parseObject(customerEntity, CustomerDTO.class);
        customerDTO.add(linkTo(methodOn(CustomerController.class).findById(id)).withSelfRel());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> findCustomersByUserId(Long userId) {
        var customersDTOs = parseListObjects(customerRepository.findByUserId(userId), CustomerDTO.class);

        for (CustomerDTO dto: customersDTOs) {
            dto.add(linkTo(methodOn(CustomerController.class)
                    .findById(dto.getId()))
                    .withSelfRel());
        }

        return customersDTOs;
    }

    @Override
    public CustomerDTO create(Long userId, CustomerDTO dto) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        var customerEntity = parseObject(dto, Customer.class);
        customerEntity.setUser(userEntity);
        var customerDTO = parseObject(customerRepository.save(customerEntity), CustomerDTO.class);

        customerDTO.add(linkTo(methodOn(CustomerController.class)
                .findById(customerDTO.getId()))
                .withSelfRel());

        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
        var entity = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, dto.getId())));

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        var customerDTO = parseObject(customerRepository.save(entity), CustomerDTO.class);
        customerDTO.add(linkTo(methodOn(CustomerController.class).findById(customerDTO.getId())).withSelfRel());
        return customerDTO;
    }

    @Override
    public void deleteByCustomerId(Long id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, id)));

        customerRepository.delete(entity);
    }
}
