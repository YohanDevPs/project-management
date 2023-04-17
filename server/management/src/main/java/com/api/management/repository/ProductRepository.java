package com.api.management.repository;

import com.api.management.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    Set<Product> findByBusinessId(Long productId);
}


