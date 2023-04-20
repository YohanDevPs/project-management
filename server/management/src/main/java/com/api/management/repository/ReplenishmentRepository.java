package com.api.management.repository;

import com.api.management.model.Replenishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReplenishmentRepository extends JpaRepository<Replenishment, Long> {

    Map<Long, List<Replenishment>> findByCustomer_Id(Long id);
    Map<Long, List<Replenishment>> findByCustomerId(Long id);
}
