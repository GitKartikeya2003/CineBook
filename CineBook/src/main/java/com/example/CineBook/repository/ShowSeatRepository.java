package com.example.CineBook.repository;

import com.example.CineBook.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {



    List<ShowSeat> findForUpdate(Long showId, List<String> labels);
}
