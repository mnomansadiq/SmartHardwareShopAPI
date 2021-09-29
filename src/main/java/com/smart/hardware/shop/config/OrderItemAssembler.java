package com.smart.hardware.shop.config;

import com.smart.hardware.shop.dto.CustomerOrderEntity;
import com.smart.hardware.shop.dto.ProductEntity;
import com.smart.hardware.shop.model.OrderItem;
import com.smart.hardware.shop.model.Product;
import com.smart.hardware.shop.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderItemAssembler  {

    public CustomerOrderEntity toModel(OrderItem orderItem) {

        List<ProductEntity> productEntities = new ArrayList<>();
        List<Product> products = orderItem.getProducts();
        products.forEach(product -> {
            ProductEntity productEntity = new ProductEntity();
            productEntity.setId(product.getId());
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntities.add(productEntity);
        });

        User user = orderItem.getUser();

        CustomerOrderEntity orderItemEntity = new CustomerOrderEntity();
        orderItemEntity.setUserId(user.getId());
        orderItemEntity.setName(user.getName());
        orderItemEntity.setProducts(productEntities);
        orderItemEntity.setOrderDate(orderItem.getOrderDate());
        return orderItemEntity;
    }
}
