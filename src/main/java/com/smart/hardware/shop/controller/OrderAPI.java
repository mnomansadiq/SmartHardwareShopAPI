package com.smart.hardware.shop.controller;

import com.smart.hardware.shop.config.OrderItemAssembler;
import com.smart.hardware.shop.dto.CustomerOrderEntity;
import com.smart.hardware.shop.model.OrderItem;
import com.smart.hardware.shop.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/orders")
public class OrderAPI {

    private final OrderService orderService;
    private final OrderItemAssembler orderItemAssembler;

    public OrderAPI(OrderService orderService, OrderItemAssembler orderItemAssembler) {
        this.orderService = orderService;
        this.orderItemAssembler = orderItemAssembler;
    }

    @GetMapping
    public List<CustomerOrderEntity> getUserOrdersByDateRange(@RequestParam Long id,
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              @RequestParam LocalDate fromDate,
                                                              @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                              @RequestParam LocalDate toDate) {

        List<OrderItem> orderItems = orderService.getUserOrdersByDateRange(id, fromDate, toDate);

        return orderItems.stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public List<CustomerOrderEntity> getOrderByUser(@PathVariable Long id) {
        List<OrderItem> orderItems = orderService.getOrdersByUserId(id);
        if (orderItems.isEmpty())
            return Collections.emptyList();

        List<CustomerOrderEntity> productEntities = orderItems.stream()
                .map(orderItemAssembler::toModel)
                .collect(Collectors.toList());

        return productEntities;
    }

    @PostMapping("/create")
    public ResponseEntity<?> doAddOrder(@RequestBody CustomerOrderEntity orderItemEntity) {
        OrderItem saved = orderService.createOrder(orderItemEntity);
        if (saved == null) {
            log.error("Exception while access customer order ");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found.");
        }
        CustomerOrderEntity res = new OrderItemAssembler().toModel(saved);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
