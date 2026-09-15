package com.example.CineBook.repository;

import com.example.CineBook.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByActiveTrueOrderByTitle();
}
