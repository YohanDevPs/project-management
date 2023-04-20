package com.api.management.service.replenishment;

import com.api.management.dto.ReplenishmentDTO;

import java.util.List;

public interface ReplenishmentService {

    ReplenishmentDTO findById(Long id);
    List<ReplenishmentDTO> findReplenishmentListBySupplierId(Long customerId);
    ReplenishmentDTO create(Long customerId, ReplenishmentDTO dto);
    ReplenishmentDTO update(ReplenishmentDTO dto);
    void delete(Long id);
}
