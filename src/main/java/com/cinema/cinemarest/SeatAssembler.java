package com.cinema.cinemarest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class SeatAssembler implements RepresentationModelAssembler<Seat, EntityModel<Seat>> {

    @Override
    public EntityModel<Seat> toModel(Seat entity) {
        return EntityModel.of(
                entity,
                linkTo(WebMvcLinkBuilder.methodOn(SeatController.class).One(entity.getNumber())).withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(SeatController.class).getAll()).withRel("AllSeats")
        );
    }
}
