package com.api.management.mapper;

import com.api.management.dto.AddressDTO;
import com.api.management.mocks.MockAddress;
import com.api.management.models.Address;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.api.management.util.mapper.UtilModelMapper.parseListObjects;
import static com.api.management.util.mapper.UtilModelMapper.parseObject;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilModelMapperTest {
    MockAddress inputAddress;

    @BeforeEach
    public void setUp() {
        inputAddress = new MockAddress();
    }

    @Test
    public void parseEntityToDTOTest() {
        var output = parseObject(inputAddress.mockEntity(), AddressDTO.class);

        assertEquals(Long.valueOf(0L), output.getId());

        assertEquals("Street Fairview0", output.getStreet());
        assertEquals(0, output.getNumber());
        assertEquals("Complement0", output.getComplement());
        assertEquals("Montglomery0", output.getCity());
        assertEquals("Alabama0", output.getState());
        assertEquals("43-123310", output.getZipcode());
    }

    @Test
    public void parseEntityListToDTOListTest() {
        var outputList = parseListObjects(inputAddress.mockEntityList(), AddressDTO.class);

        var outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Street Fairview0", outputZero.getStreet());
        assertEquals(0, outputZero.getNumber());
        assertEquals("Complement0", outputZero.getComplement());
        assertEquals("Montglomery0", outputZero.getCity());
        assertEquals("Alabama0", outputZero.getState());
        assertEquals("43-123310", outputZero.getZipcode());

        var outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Street Fairview7", outputSeven.getStreet());
        assertEquals(7, outputSeven.getNumber());
        assertEquals("Complement7", outputSeven.getComplement());
        assertEquals("Montglomery7", outputSeven.getCity());
        assertEquals("Alabama7", outputSeven.getState());
        assertEquals("43-123317", outputSeven.getZipcode());
    }

    @Test
    public void parseDTOToEntityTest() {
        var output = parseObject(inputAddress.mockVO(), Address.class);
        assertEquals(Long.valueOf(0L), output.getId());
        assertEquals("Street Fairview0", output.getStreet());
        assertEquals(0, output.getNumber());
        assertEquals("Complement0", output.getComplement());
        assertEquals("Montglomery0", output.getCity());
        assertEquals("Alabama0", output.getState());
        assertEquals("43-123310", output.getZipcode());
    }

    @Test
    public void parserDTOListToEntityListTest() {
        var outputList = parseListObjects(inputAddress.mockVOList(), Address.class);
        var outputZero = outputList.get(0);

        assertEquals(Long.valueOf(0L), outputZero.getId());
        assertEquals("Street Fairview0", outputZero.getStreet());
        assertEquals(0, outputZero.getNumber());
        assertEquals("Complement0", outputZero.getComplement());
        assertEquals("Montglomery0", outputZero.getCity());
        assertEquals("Alabama0", outputZero.getState());
        assertEquals("43-123310", outputZero.getZipcode());

        var outputSeven = outputList.get(7);

        assertEquals(Long.valueOf(7L), outputSeven.getId());
        assertEquals("Street Fairview7", outputSeven.getStreet());
        assertEquals(7, outputSeven.getNumber());
        assertEquals("Complement7", outputSeven.getComplement());
        assertEquals("Montglomery7", outputSeven.getCity());
        assertEquals("Alabama7", outputSeven.getState());
        assertEquals("43-123317", outputSeven.getZipcode());
    }
}
