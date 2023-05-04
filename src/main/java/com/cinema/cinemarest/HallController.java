package com.cinema.cinemarest;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/hall")
@AllArgsConstructor
public class HallController {

    private final HallRepository hallRepository;
    private final HallAssembler hallAssembler;

    @GetMapping("/{id}")
    HallModel One(@PathVariable int id){
        Optional<Hall> temp = hallRepository.findById(id);

        return temp.map(hallAssembler::toModel).orElseThrow(() -> new HallNotFoundException(id));
    }

    @GetMapping()
    CollectionModel<HallModel> getAll(){
        List<HallModel> halls = hallRepository.findAll().stream()
                .map(hallAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                halls,
                linkTo(methodOn(HallController.class).getAll()).withSelfRel(),
                linkTo(RootController.class).withRel("Root")
        );
    }
}
