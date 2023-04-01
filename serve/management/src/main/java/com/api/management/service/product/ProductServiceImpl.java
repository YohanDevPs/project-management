package com.api.management.service.product;

import com.api.management.model.Product;
import com.api.management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductServiceImpl implements ProductSevice {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

//    @Override
//    public Set<Product> findProductSetByBusinessId(Long businessId) {
//        return productRepository.findByBusinessId(businessId);
//    }

    @Override
    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public void deleteProductById(Long businessId) {
        productRepository.deleteById(businessId);
    }
}
