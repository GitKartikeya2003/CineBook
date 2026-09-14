package com.example.CineBook.service;


import com.example.CineBook.dtos.BookingResponse;
import com.example.CineBook.dtos.CreateBookingRequest;
import com.example.CineBook.entity.Booking;
import com.example.CineBook.entity.Show;
import com.example.CineBook.entity.ShowSeat;
import com.example.CineBook.exception.ResourceNotFoundException;
import com.example.CineBook.exception.SeatUnavailableException;
import com.example.CineBook.repository.BookingRepository;
import com.example.CineBook.repository.CustomerRepository;
import com.example.CineBook.repository.ShowRepository;
import com.example.CineBook.repository.ShowSeatRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

@Service
public class BookingService {

    private final ShowRepository showRepository;

    private final ShowSeatRepository showSeatRepository;
    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;


    public BookingService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRepository bookingRepository, CustomerRepository customerRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
    }

    @Transactional
    public BookingResponse booking(Long showId, CreateBookingRequest request) {

        Show show = showRepository.findById(showId).orElseThrow(
                () -> new ResourceNotFoundException("show not found"));

        var customer = customerRepository.findById(request.profileId()).orElseThrow(
                () -> new ResourceNotFoundException("Profile not found"));

        List<String> labels = request.seatLabels().stream()
                .map(label -> label.trim().toUpperCase(Locale.ROOT)).toList();

        //Locale.ROOT ensures the uppercase conversion works identically on any computer,
        // regardless of the system's regional language settings.


        if (labels.stream().distinct().count() != labels.size()) {

            throw new SeatUnavailableException("Duplicate Seats are not allowed");
        }

        List<ShowSeat> seats = showSeatRepository.findForUpdate(showId, labels);

        if (seats.size() != labels.size() || seats.stream().anyMatch(ShowSeat::isReserved)) {

            throw new SeatUnavailableException("One or more seats are unavailable");
        }

        seats.forEach(ShowSeat::reserve);
        show.reserve(labels.size());

        BigDecimal totalPrice = show.getTicketPrice().multiply(new BigDecimal(seats.size()));

        Booking booking = bookingRepository.save(new Booking(show, customer, totalPrice, labels));

        return BookingResponse.from(booking);
    }


}
