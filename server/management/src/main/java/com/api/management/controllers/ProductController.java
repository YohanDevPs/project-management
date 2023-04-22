package com.api.management.controllers;

import com.api.management.dto.ProductDTO;
import com.api.management.services.product.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.api.management.util.constants.UtilMediaType.*;


@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductSevice productSevice;

    @GetMapping(value = "/replenishment/{replenishmentId}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductByReplenishmentId(@PathVariable("replenishmentId") Long replenishmentId) {
        return productSevice.findProductsByReplenishmentId(replenishmentId);
    }

    @GetMapping(value = "/sale/{saleId}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductBySaleId(@PathVariable("saleId") Long saleId) {
        return productSevice.findProductsBySaleId(saleId);
    }

    @GetMapping(value = "/{id}", produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
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

    @PostMapping(value = "/sale/{id}",
            consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProductToSale(@PathVariable("id") Long id, @RequestBody ProductDTO dto) {
        return productSevice.createProductToSale(id, dto);
    }

    @PutMapping(consumes = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML},
            produces = { APPLICATION_JSON, APPLICATION_XML, APPLICATION_YML})
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update( @RequestBody ProductDTO dto) {
        return productSevice.update(dto);
    }
}
