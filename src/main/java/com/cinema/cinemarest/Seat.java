package com.cinema.cinemarest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "Seats")
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public class Seat {

    @Id
    private int number;
    private int movieId;
    private String firstName;
    private String secondName;
    private int taken;

    public Seat(){}
}
