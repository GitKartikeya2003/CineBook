package com.example.CineBook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "shows")
public class Show {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Theatre theatre;

    private LocalDateTime startsAt;
    private LocalDateTime endsAt;

    private BigDecimal ticketPrice;

    private int totalSeats;
    private int availableSeats;

    private boolean active = true;

    @Version
    private int version;

    public void reserve(int seats) {

        if (seats <= 0 || seats > availableSeats) {

            throw new IllegalArgumentException();
        }

        availableSeats -= seats;

    }

    public void release(int seats) {

        availableSeats = availableSeats + seats;

    }

    public Show(Movie movie, Theatre theatre, LocalDateTime startsAt, LocalDateTime endsAt, BigDecimal ticketPrice, int totalSeats) {
        this.movie = movie;
        this.theatre = theatre;
        this.startsAt = startsAt;
        this.endsAt = endsAt;
        this.totalSeats = totalSeats;
        this.ticketPrice = ticketPrice;
        this.availableSeats = totalSeats;
    }
}
