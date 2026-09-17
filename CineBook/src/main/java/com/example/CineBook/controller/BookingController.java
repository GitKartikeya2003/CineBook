package com.example.CineBook.controller;


import com.example.CineBook.dtos.BookingResponse;
import com.example.CineBook.dtos.CreateBookingRequest;
import com.example.CineBook.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {


    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PostMapping("/shows/{showId}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingResponse book(@PathVariable long showId, @RequestBody CreateBookingRequest createBookingRequest) {

        return bookingService.booking(showId, createBookingRequest);

    }


    @PostMapping("/{bookingId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public BookingResponse cancel(@PathVariable Long bookingId, @PathVariable Long profileId) {

        return bookingService.cancel(bookingId, profileId);

    }

    @GetMapping("/{bookingId}")
    public BookingResponse find(@PathVariable Long bookingId) {

        return bookingService.find(bookingId);

    }


}
