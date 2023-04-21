package com.api.management.service.customer;

import com.api.management.controllers.CustomerController;
import com.api.management.dto.CustomerDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.exceptions.UserNotFoundException;
import com.api.management.models.Customer;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import static com.api.management.mapper.UtilModelMapper.parseObject;
import static com.api.management.mapper.UtilModelMapper.parseSetObjects;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String CUSTOMER_NOT_FOUND_MSG = "Customer com id [%d] not found";
    public static final String USER_NOT_FOUND_MSG = "User if id [%d] not found";
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerDTO findCustomerById(Long id) {
        var customerEntity = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, id)));

        var customerDTO = parseObject(customerEntity, CustomerDTO.class);
        customerDTO.add(linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());
        return customerDTO;
    }

    @Override
    public Set<CustomerDTO> findCustomerSetByUserId(Long userId) {
        var customerList = parseSetObjects(customerRepository.findByUserId(userId), CustomerDTO.class);

        for (CustomerDTO dto: customerList) {
            dto.add(linkTo(methodOn(CustomerController.class)
                    .getCustomerById(dto.getId()))
                    .withSelfRel());
        }

        return parseSetObjects(customerRepository.findByUserId(userId), CustomerDTO.class);
    }

    @Override
    public CustomerDTO create(Long userId, CustomerDTO dto) {
        var userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(USER_NOT_FOUND_MSG, userId)));

        var customerEntity = parseObject(dto, Customer.class);
        customerEntity.setUser(userEntity);
        var customerDTO = parseObject(customerRepository.save(customerEntity), CustomerDTO.class);

        customerDTO.add(linkTo(methodOn(CustomerController.class)
                .getCustomerById(customerDTO.getId()))
                .withSelfRel());

        return customerDTO;
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
        var entity = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, dto.getId())));

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        var customerDTO = parseObject(customerRepository.save(entity), CustomerDTO.class);
        customerDTO.add(linkTo(methodOn(CustomerController.class).getCustomerById(customerDTO.getId())).withSelfRel());
        return customerDTO;
    }

    @Override
    public void deleteByCustomerId(Long id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, id)));

        customerRepository.delete(entity);
    }
}
