package com.api.management.controllers;


import com.api.management.dto.ReplenishmentDTO;
import com.api.management.service.replenishment.ReplenishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replenishment")
public class ReplenishmentController {

    @Autowired
    private ReplenishmentService service;

    @GetMapping("/supplier/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<ReplenishmentDTO> findReplenishmentBySupplierId(@PathVariable("id") Long id) {
        return service.findReplenishmentListBySupplierId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ReplenishmentDTO findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ReplenishmentDTO create(@PathVariable("id") Long id, @RequestBody ReplenishmentDTO dto) {
        return service.create(id, dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ReplenishmentDTO update(@RequestBody ReplenishmentDTO dto) {
        return service.update(dto);
    }
}
