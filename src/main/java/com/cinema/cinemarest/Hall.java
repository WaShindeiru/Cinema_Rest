package com.cinema.cinemarest;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="Hall")
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Hall {

    @Id
    private int hallId;
    private String name;
    private int capacity;
}
