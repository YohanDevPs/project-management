package com.api.management.repositorys;

import com.api.management.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySaleId(Long productId);
    List<Product> findByReplenishmentId(Long productId);
}


