package com.smart.hardware.shop.service;

import com.smart.hardware.shop.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(long id);

    Product createProduct(Product product);

    void deleteProduct(long id);

    List<Product> productSearch(Specification<Product> specs);
}
