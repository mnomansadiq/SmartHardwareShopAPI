package com.smart.hardware.shop.service;


import com.smart.hardware.shop.exception.ResourceNotFoundException;
import com.smart.hardware.shop.model.Product;
import com.smart.hardware.shop.repository.ProductRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(long id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product not found with id %d", id)));
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(long id) {
        Product product = getProduct(id);
        productRepository.delete(product);
    }
    
    @Override
    public List<Product> productSearch(Specification<Product> specs) {
        return productRepository.findAll(specs);
                 
    }

    
}
