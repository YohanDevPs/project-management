package com.api.management.repository;

import com.api.management.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Set<Address> findByCustomerId(Long userId);
}
