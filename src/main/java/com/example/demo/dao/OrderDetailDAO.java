package com.example.demo.dao;

import com.example.demo.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailDAO extends JpaRepository
        <OrderDetail, Long> {
}
