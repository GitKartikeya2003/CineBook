package com.example.CineBook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies", uniqueConstraints = @UniqueConstraint(name = "uk_movie_title", columnNames = "title"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;


    private String language;
    private String genre;

    private Integer durationMinutes;

    private String certificate;

    private String description;

    private String posterUrl;

    private String trailerUrl;

    private boolean active = true;
}
