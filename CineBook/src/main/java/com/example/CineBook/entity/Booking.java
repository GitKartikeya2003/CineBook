package com.example.CineBook.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Booking {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Show show;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Customer customer;

    private String customerName;
    private String customerEmail;
    private String customerPhone;
    private BigDecimal totalAmount;
    private LocalDateTime bookedAt;


    @Enumerated(EnumType.STRING)
    private BookingStatus status;


    @ElementCollection
    @CollectionTable(

            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id ")
    )
    @Column(name = "seat_label", nullable = false)
    public List<String> seatLabels = new ArrayList<>();

    public Booking(Show show, String customerName, String customerEmail, String customerPhone, BigDecimal totalAmount, List<String> seatLabels) {
        this.show = show;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.totalAmount = totalAmount;
        this.bookedAt = LocalDateTime.now();
        this.seatLabels = new ArrayList<>(seatLabels);
        this.status = BookingStatus.CONFIRMED;
    }

    public Booking(Show show, Customer customer, BigDecimal totalAmount, List<String> seatLabels) {
        this(show, customer.getName(), customer.getEmail(), customer.getPhone(), totalAmount, seatLabels);
        this.customer = customer;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }


}
