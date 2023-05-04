package com.cinema.cinemarest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class Advice {

    @ResponseBody
    @ExceptionHandler(SeatNotFoundException.class)
    ResponseEntity<?> adviceSeat(SeatNotFoundException ex){
        ResponseEntity<?> temp = ResponseEntity.notFound()
                .build();

        return temp;
    }

    @ResponseBody
    @ExceptionHandler(MovieNotFoundException.class)
    ResponseEntity<?> adviceMovie(MovieNotFoundException ex){
        ResponseEntity<String> temp = new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
        return temp;
    }
}
