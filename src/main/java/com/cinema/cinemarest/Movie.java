package com.cinema.cinemarest;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Table(name="Movie")
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Movie {

    @Id
    private int movieId;
    private String name;
    private int hallId;
}
