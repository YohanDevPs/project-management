package com.api.management.service.product;

import com.api.management.dto.ProductDTO;

import java.util.List;

public interface ProductSevice {

    ProductDTO findById(Long productId);
    List<ProductDTO> findProductsBySaleId(Long businessId);
    List<ProductDTO> findProductsByReplenishmentId(Long businessId);
    ProductDTO createProductToReplenishment(Long replenishmentId, ProductDTO product);
    ProductDTO createProductToSale(Long saleId, ProductDTO product);
    ProductDTO update(ProductDTO product);
    void delete(Long businessId);
}
