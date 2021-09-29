package com.smart.hardware.shop.config;


import com.smart.hardware.shop.dto.ProductEntity;
import com.smart.hardware.shop.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler {

    public ProductEntity toModel(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setDescription(product.getDescription());
        productEntity.setName(product.getName());
        productEntity.setPrice(product.getPrice());
        return productEntity;
    }
}
