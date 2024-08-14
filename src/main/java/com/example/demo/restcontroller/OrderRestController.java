package com.example.demo.restcontroller;

import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
    @Autowired
    OrderService orderService;

    @PostMapping("/rest/orders")
    public Order create(@RequestBody JsonNode orderData) {
        System.out.println("Received order data: " + orderData);
        return orderService.create(orderData);
    }
}
