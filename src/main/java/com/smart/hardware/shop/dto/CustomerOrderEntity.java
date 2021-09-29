package com.smart.hardware.shop.dto;

import java.util.Date;
import java.util.List;

public class CustomerOrderEntity {

    private Long userId;

    private String name;

    private List<ProductEntity> products;

    private Date orderDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductEntity> getProducts() {
        return products;
    }

    public void setProducts(List<ProductEntity> products) {
        this.products = products;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate =orderDate;
    }
}
