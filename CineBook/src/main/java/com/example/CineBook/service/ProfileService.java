package com.example.CineBook.service;


import com.example.CineBook.dtos.CreateProfileRequest;
import com.example.CineBook.dtos.ProfileResponse;
import com.example.CineBook.entity.Customer;
import com.example.CineBook.exception.ProfileConflictException;
import com.example.CineBook.exception.ResourceNotFoundException;
import com.example.CineBook.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ProfileService {

    private final CustomerRepository customerRepository;


    public ProfileService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public ProfileResponse create(CreateProfileRequest request) {

        String email = request.email().trim().toLowerCase(Locale.ROOT);

        String phone = request.phone();

        if (customerRepository.existsByEmail(email)) {

            throw new ProfileConflictException("Email already exists");
        }

        if (customerRepository.existsByPhone(phone)) {
            throw new ProfileConflictException("Phone already exists");
        }
        return ProfileResponse.from(customerRepository.save(new Customer(request.name().trim(), email, phone)));

    }

    public ProfileResponse login(String identifier) {

        String value = identifier == null ? "" : identifier.trim();

        String phone = value.replaceAll("\\D", "");
        Customer customer = value.contains("@") ? customerRepository.findByEmail(value) : customerRepository.findByPhone(phone);

        if (customer == null) {
            throw new ResourceNotFoundException("Customer not found");
        }

        return ProfileResponse.from(customer);
    }

    public String normalizePhone(String phone) {

        String normalize = phone == null ? "" : phone.replaceAll("\\D", "");

        if (normalize.length() < 10 || normalize.length() >= 15) {

            throw new IllegalArgumentException("Enter valid phone number");
        }
        return normalize;
    }


}
