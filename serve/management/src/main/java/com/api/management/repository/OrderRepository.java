package com.api.management.repository;

import com.api.management.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {
    Set<Order> findByBusinessId(Long userId);
}
