package com.cinema.cinemarest;

import org.aspectj.weaver.ast.Var;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/seats")
public class SeatController {

    private final SeatRepository seatRepository;
    private final SeatAssembler seatAssembler;

    public SeatController(SeatRepository seatRepository, SeatAssembler seatAssembler) {
        this.seatRepository = seatRepository;
        this.seatAssembler = seatAssembler;
    }

    @GetMapping()
    CollectionModel<EntityModel<Seat>> getAll(){
        List<EntityModel<Seat>> temp = seatRepository.findAll().stream()
                .map(seatAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(
                temp,
                linkTo(methodOn(SeatController.class).getAll()).withSelfRel(),
                linkTo(RootController.class).withRel("Root")
        );
    }

    @GetMapping("/movie/{movieId}")
    CollectionModel<EntityModel<Seat>> getByMovieId(@PathVariable int movieId){
        List<EntityModel<Seat>> seats = seatRepository.findByMovieIdEquals(movieId)
                .stream()
                .map(seatAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(seats, linkTo(methodOn(SeatController.class).getByMovieId(movieId)).withSelfRel());
    }

    @GetMapping("/{id}")
    EntityModel<Seat> One(@PathVariable int id){
        var temp = seatRepository.findById(id);

        return temp.map(seatAssembler::toModel).orElseThrow(() -> new SeatNotFoundException(id));
    }

    @PutMapping("/{id}/book")
    EntityModel<Seat> bookSeat(@PathVariable int id){
        var tempSeat = seatRepository.findById(id);

        Seat seat = tempSeat.orElseThrow(() -> new SeatNotFoundException(id));

        if(seat.getTaken() == 0){
            seat.setTaken(1);
            return seatAssembler.toModel(seatRepository.save(seat));
        }

        seat.setFirstName("Invalid");
        seat.setSecondName("Invalid");
        seat.setNumber(-1);
        seat.setMovieId(-1);
        return seatAssembler.toModel(seat);
    }

    @PutMapping("/{id}/free")
    EntityModel<Seat> freeSeat(@PathVariable int id){
        Seat seat = seatRepository.findById(id).orElseThrow(() -> new SeatNotFoundException(id));

        if(seat.getTaken() == 1){
            seat.setTaken(0);
            return seatAssembler.toModel(seatRepository.save(seat));
        }

        seat.setFirstName("Invalid");
        seat.setSecondName("Invalid");
        seat.setNumber(-1);
        seat.setMovieId(-1);
        return seatAssembler.toModel(seat);
    }
}
