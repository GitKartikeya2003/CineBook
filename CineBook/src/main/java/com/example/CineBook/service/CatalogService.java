package com.example.CineBook.service;


import com.example.CineBook.dtos.MovieResponse;
import com.example.CineBook.dtos.ShowResponse;
import com.example.CineBook.dtos.TheatreResponse;
import com.example.CineBook.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CatalogService {


    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final MovieRepository movieRepository;
    private final TheatreRepository theatreRepository;


    public CatalogService(ShowRepository showRepository, ShowSeatRepository showSeatRepository, MovieRepository movieRepository, TheatreRepository theatreRepository) {
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.movieRepository = movieRepository;
        this.theatreRepository = theatreRepository;
    }

    public List<MovieResponse> movies() {

        return movieRepository.findByActiveTrueOrderByTitle().stream().map(MovieResponse::from).toList();
    }

    public List<TheatreResponse> theatres(String city) {

        return theatreRepository.findByCityIgnoreCaseOrderByName(city).stream().map(TheatreResponse::from).toList();
    }

    public List<ShowResponse> shows(String city, LocalDate date) {

        LocalDateTime from = date.atStartOfDay();

        return showRepository.findActiveShows(city, from, from.plusDays(1)).stream()
                .map(show -> ShowResponse.from(show, showSeatRepository.findAvailableLabels(show.getId())))
                .toList();

    }


}
