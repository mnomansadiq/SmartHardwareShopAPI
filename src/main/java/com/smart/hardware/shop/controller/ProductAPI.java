package com.smart.hardware.shop.controller;

import com.sipios.springsearch.anotation.SearchSpec;
import com.smart.hardware.shop.config.ProductModelAssembler;
import com.smart.hardware.shop.dto.ProductEntity;
import com.smart.hardware.shop.model.Product;
import com.smart.hardware.shop.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Slf4j
@RestController
@RequestMapping("/api/v1/products")
public class ProductAPI {
    private int rand = 0;
    private final ProductService productService;

    private final ProductModelAssembler productModelAssembler;

    public ProductAPI(ProductService productService, ProductModelAssembler productModelAssembler) {
        this.productService = productService;
        this.productModelAssembler = productModelAssembler;
    }
    
    @GetMapping("/")
    public List<ProductEntity> getProducts() {
        List<Product> products = productService.getAllProducts();

        return products.stream()
                .map(productModelAssembler::toModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EntityModel<Product> getOne(@PathVariable Long id) {
        Product product = productService.getProduct(id);
        return EntityModel.of(product);
    }

    @PostMapping("/create")
    EntityModel<Product> create(@RequestBody ProductEntity p) {
        return EntityModel.of(productService.createProduct(new Product(p.getName(),p.getDescription(),p.getPrice())));
    }

    @GetMapping("/delete/{id}")
    public Map<String, String> delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        Map map = new HashMap<String, String>();
        map.put("message", "product deleted successfully");
        return map;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(@SearchSpec Specification<Product> specs) {
        return new ResponseEntity(productService.productSearch(specs), HttpStatus.OK);
    }

}
