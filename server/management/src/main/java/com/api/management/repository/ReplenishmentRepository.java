package com.api.management.repository;

import com.api.management.model.Replenishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplenishmentRepository extends JpaRepository<Replenishment, Long> {

    List<Replenishment> findBySupplierId(Long id);
}
