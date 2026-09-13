package com.example.CineBook.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "show_seats", uniqueConstraints = @UniqueConstraint(name = "uk_show_seat", columnNames = {"show_id", "seatLabel"}))
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Show show;

    private String seatLabel;

    private boolean reserved;

    public void reserve()
    {
        reserved=true;
    }

    public void release()
    {
        reserved=false;
    }


}
