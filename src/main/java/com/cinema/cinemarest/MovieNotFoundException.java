package com.cinema.cinemarest;

public class MovieNotFoundException extends RuntimeException{

    public MovieNotFoundException(int id){
        super("Movie not found with given id: " + id);
    }
}
