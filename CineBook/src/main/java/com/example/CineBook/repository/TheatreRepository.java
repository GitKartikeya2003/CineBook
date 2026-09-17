package com.example.CineBook.repository;

import com.example.CineBook.entity.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    List<Theatre> findByCityIgnoreCaseOrderByName(String city);
}
