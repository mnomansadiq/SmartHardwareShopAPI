package com.smart.hardware.shop.config;

import com.smart.hardware.shop.model.OrderItem;
import com.smart.hardware.shop.model.Product;
import com.smart.hardware.shop.model.User;
import com.smart.hardware.shop.repository.OrderRepository;
import com.smart.hardware.shop.repository.ProductRepository;
import com.smart.hardware.shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Configuration
class LoadDatabase {
    private static final Logger LOG = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        return args -> {

            // user init
            User user1 = new User("Noman");
            User user2 = new User("Alex");
            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.findAll().forEach(user -> LOG.info("loaded customers {}", user));

            // product init
            Product product1 = new Product("Jig-saw", "This 20V MAX Jig Saw has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes..", new BigDecimal("80.99"));
            Product product2 = new Product("Leaf-Blower", "The EGO POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed", new BigDecimal("109.90"));
            Product product3 = new Product("pip-cutter", "This 120V MAX pip cutter has an all metal, lever-action keyless blade clamp designed for quick and easy blade changes..", new BigDecimal("180.99"));
            Product product4 = new Product("screwdriver", "The POWER+ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed", new BigDecimal("209.90"));
            Product product5 = new Product("screwdriver", "The POWER++ string trimmer and blower combo kit are the perfect pair to get all your yard maintenance completed", new BigDecimal("509.90"));

            productRepository.save(product1);
            productRepository.save(product2);
            productRepository.save(product3);
            productRepository.save(product4);
            productRepository.save(product5);

            // user order
            List<Product> productSet = new ArrayList<>();
            productSet.add(product1);
            productSet.add(product2);

            List<Product> productSet2 = new ArrayList<>();
            productSet2.add(product3);
            productSet2.add(product4);

            GregorianCalendar date = new GregorianCalendar(2020, Calendar.FEBRUARY, 10);
             
            orderRepository.save(new OrderItem(user1, productSet, date.getTime()));
            orderRepository.save(new OrderItem(user1, productSet2, new Date()));

            orderRepository.save(new OrderItem(user2, productSet2, new Date()));

            orderRepository.findAll().forEach(order -> LOG.info("Preloaded order {}", order));

            orderRepository.findOrdersByUser(1L).forEach(order -> LOG.info("Preloaded orderByUser {}", order));

            orderRepository.findUserOrdersByDateRange(1L, LocalDate.now(), LocalDate.now())
                    .forEach(order -> LOG.info("Preloaded userOrderByDate {}", order));
        };
    }
}