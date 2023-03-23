package com.api.management.controller;

import com.api.management.model.Order;
import com.api.management.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/allByUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Set<Order> getOrdersByBusinessId(@PathVariable("id") Long id) {
        return orderService.findOrderSetByBusinessId(id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderById(@PathVariable("id") Long id) {
        return orderService.findOrderById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveOrder(@RequestBody Order order) {
        orderService.saveOrder(order);
    }
}
