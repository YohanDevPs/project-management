package com.api.management.service.order;

import com.api.management.model.Order;

import java.util.Set;

public interface OrderService {
    Order findOrderById(Long orderId);
    Set<Order> findOrderSetByBusinessId(Long userId);
    void saveOrder(Order order);
    Order updateOrder(Long orderId);
    void deleteOrderById(Long orderId);
}
