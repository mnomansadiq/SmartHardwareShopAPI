package com.smart.hardware.shop.service;

import com.smart.hardware.shop.dto.CustomerOrderEntity;
import com.smart.hardware.shop.dto.ProductEntity;
import com.smart.hardware.shop.model.OrderItem;
import com.smart.hardware.shop.model.Product;
import com.smart.hardware.shop.model.User;
import com.smart.hardware.shop.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final UserService userService;

    private final ProductService productService;

    private final OrderRepository orderRepository;

    public OrderServiceImpl(UserService userService, ProductService productService, OrderRepository orderRepository) {
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderItem> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderItem> getOrdersByUserId(Long userId) {
        return orderRepository.findOrdersByUser(userId);
    }

    @Override
    public List<OrderItem> getUserOrdersByDateRange(Long userId, LocalDate fromDate, LocalDate toDate) {
        return orderRepository.findUserOrdersByDateRange(userId, fromDate, toDate);
    }

    @Override
    public OrderItem createOrder(CustomerOrderEntity orderItemEntity) {
        Long userId = orderItemEntity.getUserId();
        User user;
        try {
            user = userService.getUser(userId);
        } catch (Exception e) {
            log.error("unable to find the user by id:{} ", userId, e);
            return null;
        }

        List<Product> products = new ArrayList<>();
        List<ProductEntity> productEntities = orderItemEntity.getProducts();
        productEntities.forEach(productEntity -> products.add(productService.getProduct(productEntity.getId())));

        OrderItem orderItem = new OrderItem(user, products, new Date());
        return orderRepository.save(orderItem);
    }

}
