package com.api.management.service.order;

import com.api.management.model.Customer;
import com.api.management.model.Order;
import com.api.management.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Set<Order> findOrderSetByBusinessId(Long id) {
        return orderRepository.findByBusinessId(id);
    }

    @Override
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Long id) {
        return null;
    }

    @Override
    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }
}
