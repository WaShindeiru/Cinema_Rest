package com.cinema.cinemarest;

public class SeatNotFoundException extends RuntimeException{

    public SeatNotFoundException(int number){
        super("Seat not found, number: " + number);
    }
}
