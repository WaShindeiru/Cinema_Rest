package com.cinema.cinemarest;

import org.aspectj.weaver.ast.Var;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SeatController {

    private final SeatRepository seatRepository;
    private final SeatAssembler seatAssembler;

    public SeatController(SeatRepository seatRepository, SeatAssembler seatAssembler) {
        this.seatRepository = seatRepository;
        this.seatAssembler = seatAssembler;
    }

    @GetMapping("/seats")
    CollectionModel<EntityModel<Seat>> getAll(){
        List<EntityModel<Seat>> temp = seatRepository.findAll().stream()
                .map(seatAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(temp, WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(SeatController.class).getAll()).withSelfRel());
    }

    @GetMapping("/seats/{id}")
    EntityModel<Seat> One(@PathVariable int number){
        var temp = seatRepository.findById(number);

        return temp.map(seatAssembler::toModel).orElseThrow(() -> new SeatNotFoundException(number));
    }
}
