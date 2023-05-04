package com.cinema.cinemarest;

import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HallAssembler implements RepresentationModelAssembler<Hall, HallModel> {

    @Override
    public HallModel toModel(Hall entity) {

        HallModel model = new HallModel(
                entity.getHallId(),
                entity.getName(),
                entity.getCapacity()
        );

        model.add(linkTo(methodOn(HallController.class).One(entity.getHallId())).withSelfRel());
        model.add(linkTo(methodOn(HallController.class).getAll()).withRel("MovieHalls"));

        return model;
    }
}
