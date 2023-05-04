package com.cinema.cinemarest;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MovieAssembler implements RepresentationModelAssembler<Movie, MovieModel>{

    @Override
    public MovieModel toModel(Movie entity) {

        MovieModel temp = new MovieModel(entity.getMovieId(), entity.getName(), entity.getHallId());

        temp.add(linkTo(methodOn(MovieController.class).One(entity.getMovieId())).withSelfRel());
        temp.add(linkTo(methodOn(MovieController.class).getAll()).withRel("AllMovies"));
        temp.add(linkTo(methodOn(SeatController.class).getByMovieId(temp.getMovieId())).withRel("MovieSeats"));
        temp.add(linkTo(methodOn(HallController.class).One(entity.getHallId())).withRel("Hall"));

        return temp;
    }

    @Override
    public CollectionModel<MovieModel> toCollectionModel(Iterable<? extends Movie> entities) {
        CollectionModel<MovieModel> temp = RepresentationModelAssembler.super.toCollectionModel(entities);
        return temp;
    }
}
