package com.api.management.service.customer;

import com.api.management.dto.CustomerDTO;
import com.api.management.exception.UserNotFoundException;
import com.api.management.model.Customer;
import com.api.management.repository.CustomerRepository;
import com.api.management.repository.UserRepository;
import com.api.management.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.api.management.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.mapper.UtilModelMapper.parseObject;

@Service
public class CustomerServiceImpl implements CustomerService {

    public static final String CUSTOMER_NOT_FOUND = "Customer com id: %d não encontrado";
    public static final String USER_NOT_FOUND = "Usuário com id: %d não encontrado";
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public CustomerDTO findCustomerById(Long id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(CUSTOMER_NOT_FOUND, id)));

        return parseObject(entity, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> findCustomerSetByUserId(Long userId) {
        return parseListObjects(customerRepository.findByUserId(userId), CustomerDTO.class);
    }

    @Override
    public CustomerDTO saveCustomer(Long idUser, CustomerDTO dto) {
        var userEntity = userRepository.findById(idUser)
                .orElseThrow(() -> new UserNotFoundException(String.format(USER_NOT_FOUND, idUser)));

        var customerEntity = parseObject(dto, Customer.class);
        customerEntity.setUser(userEntity);
        customerRepository.save(customerEntity);
        return parseObject(customerEntity, CustomerDTO.class);
    }

    @Override
    public CustomerDTO update(CustomerDTO dto) {
        var entity = customerRepository.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException(String.format(CUSTOMER_NOT_FOUND, dto.getId())));

        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());

        return parseObject(customerRepository.save(entity), CustomerDTO.class);
    }

    @Override
    public void deleteByCustomerId(Long id) {
        var entity = customerRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format(CUSTOMER_NOT_FOUND, id)));

        customerRepository.delete(entity);
    }
}
