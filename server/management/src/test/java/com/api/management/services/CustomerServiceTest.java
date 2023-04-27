package com.api.management.services;

import com.api.management.dto.CustomerDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.mocks.MockCustomer;
import com.api.management.mocks.MockUser;
import com.api.management.models.Customer;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.repositorys.UserRepository;
import com.api.management.services.customer.CustomerServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    MockCustomer inputCustomer;
    MockUser inputUser;
    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private UserRepository userRepository;

    @BeforeAll
    void setup(){
        inputCustomer = new MockCustomer();
        inputUser = new MockUser();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindCustomerById()  {
        var customer = inputCustomer.mockEntity(1);

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));
        var customerResult = customerService.findCustomerById(1L);

        assertNotNull(customerResult);
        assertNotNull(customerResult.getId());
        assertNotNull(customerResult.getLinks());
        assertEquals(customerResult.getClass(), CustomerDTO.class);

        assertTrue(customerResult.toString().contains("links: [</customer/1>;rel=\"self\"]"));

        assertEquals(1L, customerResult.getId());
        assertEquals("Yohan1", customerResult.getName());
        assertEquals("yohan@email.com1", customerResult.getEmail());
        assertEquals("71883748191", customerResult.getPhone());
    }

    @Test
    void testFindCustomersByUserId() {
        var customers = inputCustomer.mockEntityList();

        when(customerRepository.findByUserId(1L)).thenReturn(customers);

        var customersResult = customerService.findCustomersByUserId(1L);

        assertEquals(CustomerDTO.class, customersResult.get(0).getClass());
        assertNotNull(customersResult);

        var customerResultOne = customersResult.get(1);
        assertTrue(customerResultOne.toString().contains("links: [</customer/1>;rel=\"self\"]"));
        assertEquals(1L, customerResultOne.getId());
        assertEquals("Yohan1", customerResultOne.getName());
        assertEquals("yohan@email.com1", customerResultOne.getEmail());
        assertEquals("71883748191", customerResultOne.getPhone());

        var customerResultEight = customersResult.get(8);
        assertTrue(customerResultEight.toString().contains("links: [</customer/8>;rel=\"self\"]"));
        assertEquals(8L, customerResultEight.getId());
        assertEquals("Yohan8", customerResultEight.getName());
        assertEquals("yohan@email.com8", customerResultEight.getEmail());
        assertEquals("71883748198", customerResultEight.getPhone());
    }


    @Test
    void testCreateCustomer() {
        var user = inputUser.mockEntity(1);

        var customer = inputCustomer.mockEntity(1);
        var customerDTOToPersist = inputCustomer.mockDTO(1);

        when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        var result = customerService.create(1L, customerDTOToPersist);

        assertNotNull(result);
        assertNotNull(result.getLinks());
        assertEquals(CustomerDTO.class, result.getClass());

        assertTrue(result.toString().contains("links: [</customer/1>;rel=\"self\"]"));
        assertEquals(1L, result.getId());
        assertEquals("Yohan1", result.getName());
        assertEquals("yohan@email.com1", result.getEmail());
        assertEquals("71883748191", result.getPhone());
    }



    @Test
    void testCreateCustomerWithoutUser() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            customerService.create(1L, any(CustomerDTO.class));
        });

        String expectedMessage = "User if id [1] not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }


    @Test
    void testUpdateCustomer() {
        var customer = inputCustomer.mockEntity(1);
        var customerPersisted = customer;

        var dto = inputCustomer.mockDTO(1);

        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customer));
        when(customerRepository.save(ArgumentMatchers.eq(customer))).thenReturn(customerPersisted);

        var result = customerService.update(dto);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</customer/1>;rel=\"self\"]"));
        assertEquals(1L, result.getId());
        assertEquals("Yohan1", result.getName());
        assertEquals("yohan@email.com1", result.getEmail());
        assertEquals("71883748191", result.getPhone());
    }

    @Test
    void testDeleteCustomer() {
        var customer = inputCustomer.mockEntity(1);

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer));
        customerService.deleteByCustomerId(anyLong());

        verify(customerRepository, times(1)).delete(customer);
    }
}
