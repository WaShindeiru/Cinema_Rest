package com.cinema.cinemarest;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping()
class RootController {

    @GetMapping()
    RepresentationModel<?> index(){
        RepresentationModel<?> temp = new RepresentationModel<>();
        temp.add(linkTo(SeatController.class).withRel("SeatController"));
        temp.add(linkTo(MovieController.class).withRel("MovieController"));
        temp.add(linkTo(HallController.class).withRel("HallController"));

        return temp;
    }
}
