package com.api.management.services;

import com.api.management.dto.AddressDTO;
import com.api.management.exceptions.ResourceNotFoundException;
import com.api.management.mocks.MockAddress;
import com.api.management.mocks.MockCustomer;
import com.api.management.models.Address;
import com.api.management.repositorys.AddressRepository;
import com.api.management.repositorys.CustomerRepository;
import com.api.management.services.address.AddressServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

    MockAddress inputAddress;
    MockCustomer inputCustomer;
    @InjectMocks
    private AddressServiceImpl addressService;
    @Mock
    private AddressRepository addressRepository;
    @Mock
    private CustomerRepository customerRepository;

    @BeforeAll
    void setup(){
        inputAddress = new MockAddress();
        inputCustomer = new MockCustomer();
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testFindById()  {
        var address = inputAddress.mockEntity();
        address.setId(1L);

        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
        var addressResult = addressService.findById(1L);

        assertNotNull(addressResult);
        assertNotNull(addressResult.getId());
        assertNotNull(addressResult.getLinks());

        assertTrue(addressResult.toString().contains("links: [</address/1>;rel=\"self\"]"));

        assertEquals("Street Fairview0", addressResult.getStreet());
        assertEquals(0, addressResult.getNumber());
        assertEquals("Complement0", addressResult.getComplement());
        assertEquals("Montglomery0", addressResult.getCity());
        assertEquals("Alabama0", addressResult.getState());
        assertEquals("43-123310", addressResult.getZipcode());
    }

    @Test
    void testFindAddressSetByCustomerId() {
        var addresses = inputAddress.mockEntityList();
        var customerId = 1L;

        when(addressRepository.findByCustomerId(customerId)).thenReturn(addresses);

        var addressesDTOs = addressService.findAddressSetByCustomerId(customerId);
        assertNotNull(addressesDTOs);
        assertEquals(10, addressesDTOs.size());

        var addressOne = addressesDTOs.get(1);

        assertTrue(addressOne.toString().contains("links: [</address/1>;rel=\"self\"]"));

        assertEquals("Street Fairview1", addressOne.getStreet());
        assertEquals(1, addressOne.getNumber());
        assertEquals("Complement1", addressOne.getComplement());
        assertEquals("Montglomery1", addressOne.getCity());
        assertEquals("Alabama1", addressOne.getState());
        assertEquals("43-123311", addressOne.getZipcode());

        var addressSeven = addressesDTOs.get(7);

        assertTrue(addressSeven.toString().contains("links: [</address/7>;rel=\"self\"]"));

        assertEquals("Street Fairview7", addressSeven.getStreet());
        assertEquals(7, addressSeven.getNumber());
        assertEquals("Complement7", addressSeven.getComplement());
        assertEquals("Montglomery7", addressSeven.getCity());
        assertEquals("Alabama7", addressSeven.getState());
        assertEquals("43-123317", addressSeven.getZipcode());
    }

    @Test
    void testCreateAddress() {
        var customerEntity = inputCustomer.mockEntity(1);
        var addressEntity = inputAddress.mockEntity(1);
        var addressMock = inputAddress.mockDTO(1);
        var addressPersisted = addressEntity;

        when(customerRepository.findById(1L)).thenReturn(Optional.ofNullable(customerEntity));
        doReturn(addressPersisted).when(addressRepository).save(any(Address.class));

        var result = addressService.create(customerEntity.getId(), addressMock);

        assertNotNull(result);
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</address/1>;rel=\"self\"]"));
        assertEquals("Street Fairview1", result.getStreet());
        assertEquals(1, result.getNumber());
        assertEquals("Complement1", result.getComplement());
        assertEquals("Montglomery1", result.getCity());
        assertEquals("Alabama1", result.getState());
        assertEquals("43-123311", result.getZipcode());
    }

    @Test
    void testCreateWithoutCustomer() {
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            addressService.create(1L, any(AddressDTO.class));
        });

        String expectedMessage = "Customer if id [1] not found";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testUpdateAddress() {
        var address = inputAddress.mockEntity(1);
        var addressPersisted = address;

        var vo = inputAddress.mockDTO(1);

        when(addressRepository.findById(1L)).thenReturn(Optional.ofNullable(address));
        when(addressRepository.save(address)).thenReturn(addressPersisted);

        var result = addressService.update(vo);

        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getLinks());

        assertTrue(result.toString().contains("links: [</address/1>;rel=\"self\"]"));
        assertEquals("Street Fairview1", result.getStreet());
        assertEquals(1, result.getNumber());
        assertEquals("Complement1", result.getComplement());
        assertEquals("Montglomery1", result.getCity());
        assertEquals("Alabama1", result.getState());
        assertEquals("43-123311", result.getZipcode());
    }

    @Test
    void testDeleteAddress() {
        var address = inputAddress.mockEntity(1);

        when(addressRepository.findById(anyLong())).thenReturn(Optional.ofNullable(address));
        addressService.delete(anyLong());

        verify(addressRepository, times(1)).delete(address);
    }
}