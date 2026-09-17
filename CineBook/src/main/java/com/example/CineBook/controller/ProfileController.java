package com.example.CineBook.controller;


import com.example.CineBook.dtos.BookingResponse;
import com.example.CineBook.dtos.CreateProfileRequest;
import com.example.CineBook.dtos.ProfileResponse;
import com.example.CineBook.service.BookingService;
import com.example.CineBook.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {


    private final ProfileService profileService;
    private final BookingService bookingService;


    public ProfileController(ProfileService profileService, BookingService bookingService) {
        this.profileService = profileService;
        this.bookingService = bookingService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProfileResponse create(@Valid @RequestBody CreateProfileRequest createProfileRequest) {

        return profileService.create(createProfileRequest);

    }

    @GetMapping("/login")
    public ProfileResponse login(@RequestParam String identifier) {

        return profileService.login(identifier);

    }

    @GetMapping("/{profileId}/bookings")
    public List<BookingResponse> bookings(@PathVariable long profileId) {

        return bookingService.findByProfileId(profileId);

    }


}
