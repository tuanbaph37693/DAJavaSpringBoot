package com.example.demo.service;

import com.example.demo.entity.Order;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public interface OrderService {

    Order create(JsonNode orderData);

    Order findById(Long id);

    List<Order> findByUsername(String username);
}
