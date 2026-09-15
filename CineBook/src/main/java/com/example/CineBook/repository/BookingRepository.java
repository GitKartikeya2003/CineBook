package com.example.CineBook.repository;

import com.example.CineBook.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerIdOrderByBookedAtDesc(long id);

    Optional<Booking> findByIdAndCustomerId(Long id, Long customerId);
}
