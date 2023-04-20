package com.api.management.service.replenishment;

import com.api.management.dto.ReplenishmentDTO;

import java.util.Set;

public class ReplenishmentServiceImpl implements ReplenishmentService{

    @Override
    public ReplenishmentDTO findById(Long id) {
        return null;
    }

    @Override
    public Set<ReplenishmentDTO> findReplenishmentSetByCustomerId(Long customerId) {
        return null;
    }

    @Override
    public ReplenishmentDTO create(Long customerId, ReplenishmentDTO dto) {
        return null;
    }

    @Override
    public ReplenishmentDTO update(ReplenishmentDTO dto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
