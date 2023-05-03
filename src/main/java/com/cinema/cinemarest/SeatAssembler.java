package com.cinema.cinemarest;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SeatAssembler implements RepresentationModelAssembler<Seat, EntityModel<Seat>> {

    @Override
    public EntityModel<Seat> toModel(Seat entity) {
        EntityModel<Seat> temp =  EntityModel.of(
                entity,
                linkTo(WebMvcLinkBuilder.methodOn(SeatController.class).One(entity.getNumber())).withSelfRel(),
                linkTo(WebMvcLinkBuilder.methodOn(SeatController.class).getAll()).withRel("AllSeats")
        );

        if(entity.getTaken() == 0){
            temp.add(linkTo(methodOn(SeatController.class).bookSeat(entity.getNumber())).withRel("Book"));
        }

        if(entity.getTaken() == 1){
            temp.add(linkTo(methodOn(SeatController.class).freeSeat(entity.getNumber())).withRel("Free"));
        }

        return temp;
    }
}
