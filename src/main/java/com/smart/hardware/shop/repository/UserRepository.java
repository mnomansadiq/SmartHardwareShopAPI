package com.smart.hardware.shop.repository;

import com.smart.hardware.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
