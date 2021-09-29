package com.smart.hardware.shop.service;

import com.smart.hardware.shop.model.OrderItem;
import com.smart.hardware.shop.dto.CustomerOrderEntity;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    List<OrderItem> getAllOrders();

    List<OrderItem> getOrdersByUserId(Long userId);

    OrderItem createOrder(CustomerOrderEntity orderItemEntity);

    List<OrderItem> getUserOrdersByDateRange(Long userId, LocalDate fromDate, LocalDate toDate);
}
