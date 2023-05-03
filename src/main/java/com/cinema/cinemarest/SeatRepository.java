package com.cinema.cinemarest;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Integer> {

    public abstract List<Seat> findByMovieIdEquals(int movieId);
}
