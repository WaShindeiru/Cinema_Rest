package com.cinema.cinemarest;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieRepository movieRepository;
    private MovieAssembler movieAssembler;

    @Autowired
    public MovieController(MovieRepository movieRepository, MovieAssembler movieAssembler) {
        this.movieRepository = movieRepository;
        this.movieAssembler = movieAssembler;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public CollectionModel<MovieModel> getAll(){
        List<MovieModel> movies = movieRepository.findAll()
                .stream()
                .map(movieAssembler::toModel)
                .collect(Collectors.toList());

        Link temp = linkTo(methodOn(MovieController.class).getAll()).withSelfRel();

        return CollectionModel.of(
                movies,
                temp,
                linkTo(RootController.class).withRel("Root"));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public MovieModel One(@PathVariable int id){
        var movie = movieRepository.findById(id);
        return movie.map((val) -> movieAssembler.toModel(val)).orElseThrow(() -> new MovieNotFoundException(id));
    }
}
