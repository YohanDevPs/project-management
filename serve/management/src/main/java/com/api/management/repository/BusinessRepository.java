package com.api.management.repository;

import com.api.management.model.Business;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

    Set<Business> findByCustomerId(Long customerId);
}
