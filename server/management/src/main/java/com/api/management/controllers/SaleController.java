package com.api.management.controllers;

import com.api.management.dto.SaleDTO;
import com.api.management.service.sale.SaleService;
import com.api.management.util.UtilMediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.management.util.UtilMediaType.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @GetMapping(value = "/customer/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public List<SaleDTO> findListByCustomerId(@PathVariable("id") Long id) {
        return service.findListByCustomerId(id);
    }

    @GetMapping(value = "/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public SaleDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @PostMapping(value = "/{id}",
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public SaleDTO create(@PathVariable("id") Long id, @RequestBody SaleDTO dto) {
        return service.create(id, dto);
    }

    @PutMapping(consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    public SaleDTO update(@RequestBody SaleDTO SaleDTO) {
        return service.update(SaleDTO);
    }
}
