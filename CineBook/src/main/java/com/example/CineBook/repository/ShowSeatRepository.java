package com.example.CineBook.repository;

import com.example.CineBook.entity.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {


    @Query("select s from ShowSeat s where s.show.id=:showId and s.seatLabel in :labels")
    List<ShowSeat> findForUpdate(Long showId, List<String> labels);
}
