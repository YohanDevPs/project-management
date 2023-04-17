package com.api.management.service.product;

import com.api.management.model.Product;

public interface ProductSevice {

    Product findProductById(Long productId);
//    Set<Product> findProductSetByBusinessId(Long businessId);
    void saveProduct(Product product);
    Product updateProduct(Long productId, Product product);
    void deleteProductById(Long businessId);
}
