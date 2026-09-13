package com.example.CineBook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "theatres")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Theatre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String city;
    private String address;


}
