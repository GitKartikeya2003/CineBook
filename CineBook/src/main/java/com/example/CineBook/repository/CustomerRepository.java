package com.example.CineBook.repository;

import com.example.CineBook.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    Customer findByEmail(String email);

    Customer findByPhone(String phone);
}
