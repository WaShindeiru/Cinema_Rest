package com.cinema.cinemarest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
public class HallModel extends RepresentationModel<HallModel> {

    private int hallId;
    private String name;
    private int capacity;
}
