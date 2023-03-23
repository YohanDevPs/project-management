package com.api.management.controller;

import com.api.management.model.Product;
import com.api.management.service.product.ProductSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/product")
public class ProductController {
    
    @Autowired
    private ProductSevice productSevice;

    @GetMapping("/business/{businessId}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Product> getProductByBusinessId(@PathVariable("businessId") Long businessId) {
        return productSevice.findProductSetByBusinessId(businessId);
    }

    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable("productId") Long productId) {
        return productSevice.findProductById(productId);
    }

    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductId(@PathVariable("productId") Long productId) {
        productSevice.deleteProductById(productId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveProduct(@RequestBody Product product) {
        productSevice.saveProduct(product);
    }
}
