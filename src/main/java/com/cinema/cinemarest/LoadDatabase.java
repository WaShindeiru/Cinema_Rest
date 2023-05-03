package com.cinema.cinemarest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner init(SeatRepository seatRepository, MovieRepository movieRepository){
        return args -> {
            for(int i=0; i<11; i++){
                seatRepository.save(new Seat(i, 1, "lorem", "ipsum", 0));
            }

            seatRepository.save(new Seat(4, 1, "Ola", "Sroka", 1));
            seatRepository.save(new Seat(5, 1, "Maciek", "Młynarski", 1));
            seatRepository.save(new Seat(10, 1, "Mihael", "Madeline", 1));
            seatRepository.save(new Seat(3, 1, "Piotr", "Dymczyk", 1));

            movieRepository.save(new Movie(1, "One Day", 1));
            movieRepository.save(new Movie(2, "Career Opportunities", 2));
            movieRepository.save(new Movie(3, "V for vandetta", 1));
        };
    }
}
