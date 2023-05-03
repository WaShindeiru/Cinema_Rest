package com.cinema.cinemarest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class MovieModel extends RepresentationModel<MovieModel> {

    private int movieId;
    private String name;
    private int hallId;
}
