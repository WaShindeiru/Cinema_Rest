package com.cinema.cinemarest;

public class HallNotFoundException extends RuntimeException{

    public HallNotFoundException(int id){
        super("Hall not found, id: " + id);
    }
}
