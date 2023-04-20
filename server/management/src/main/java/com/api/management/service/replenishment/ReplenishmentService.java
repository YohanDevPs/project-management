package com.api.management.service.replenishment;

import com.api.management.dto.ReplenishmentDTO;

import java.util.Set;

public interface ReplenishmentService {

    ReplenishmentDTO findById(Long id);
    Set<ReplenishmentDTO> findReplenishmentSetByCustomerId(Long customerId);
    ReplenishmentDTO create(Long customerId, ReplenishmentDTO dto);
    ReplenishmentDTO update(ReplenishmentDTO dto);
    void delete(Long id);
}
