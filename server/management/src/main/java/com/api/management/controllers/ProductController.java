package com.api.management.controllers;

import com.api.management.dto.ProductDTO;
import com.api.management.service.product.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductSevice productSevice;

    @GetMapping("/replenishment/{replenishmentId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductByReplenishmentId(@PathVariable("replenishmentId") Long replenishmentId) {
        return productSevice.findProductsByReplenishmentId(replenishmentId);
    }

    @GetMapping("/sale/{saleId}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductBySaleId(@PathVariable("saleId") Long saleId) {
        return productSevice.findProductsBySaleId(saleId);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getProductById(@PathVariable("id") Long id) {
        return productSevice.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductId(@PathVariable("id") Long id) {
        productSevice.delete(id);
    }

    @PostMapping("/replenishment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProductToReplenishment(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
        return productSevice.createProductToReplenishment(id, dto);
    }

    @PostMapping("/sale/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProductToSale(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
        return productSevice.createProductToSale(id, dto);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update( @RequestBody ProductDTO dto) {
        return productSevice.update(dto);
    }
}
