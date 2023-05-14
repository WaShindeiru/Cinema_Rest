package com.cinema.cinemarest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SeatControllerTest {

    @Mock
    private SeatRepository seatRepository;
    @Mock
    private SeatAssembler seatAssembler;
    private SeatController seatController;
    @Captor
    ArgumentCaptor<Seat> seatCaptor;

    @BeforeEach
    void setUp() {
        seatController = new SeatController(seatRepository, seatAssembler);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void bookSeatWhenSeatFree() {
        Seat toSave = new Seat(1, 1, "Michael", "Stevens", 0);
        Seat expected = new Seat(1, 1, "Michael", "Stevens", 1);
        Mockito.when(seatRepository.findById(toSave.getNumber())).thenReturn(Optional.of(toSave));

        seatController.bookSeat(toSave.getNumber());

        Mockito.verify(seatRepository).save(seatCaptor.capture());
        Seat seat = seatCaptor.getValue();

        assertEquals(expected, seat);
    }

    @Test
    void bookSeatWhenSeatTaken() {
        Seat toSave = new Seat(1, 1, "Michael", "Stevens", 1);
        Seat expected = new Seat(-1, -1, "Invalid", "Invalid", 1);
        Mockito.when(seatRepository.findById(toSave.getNumber())).thenReturn(Optional.of(toSave));

        seatController.bookSeat(toSave.getNumber());

        Mockito.verify(seatAssembler).toModel(seatCaptor.capture());
        Seat seat = seatCaptor.getValue();

        assertEquals(expected, seat);
    }

    @Test
    void bookSeatWhenSeatNonexistent() {
        int toQuerry = 1;
        Mockito.when(seatRepository.findById(toQuerry)).thenReturn(Optional.empty());

        SeatNotFoundException exception = assertThrows(SeatNotFoundException.class, () -> seatController.bookSeat(toQuerry));
        assertEquals("Seat not found, number: 1", exception.getMessage());
    }

    @Test
    void freeSeatWhenSeatTaken() {
        Seat toSave = new Seat(1, 1, "Michael", "Stevens", 1);
        Seat expected = new Seat(1, 1, "Michael", "Stevens", 0);
        Mockito.when(seatRepository.findById(toSave.getNumber())).thenReturn(Optional.of(toSave));

        seatController.freeSeat(toSave.getNumber());

        Mockito.verify(seatRepository).save(seatCaptor.capture());
        Seat received = seatCaptor.getValue();

        assertEquals(expected, received);
    }

    @Test
    void freeSeatWhenSeatFree() {
        Seat toSave = new Seat(1, 1, "Michael", "Stevens", 0);
        Seat expected = new Seat(-1, -1, "Invalid", "Invalid", 0);
        Mockito.when(seatRepository.findById(toSave.getNumber())).thenReturn(Optional.of(toSave));

        seatController.freeSeat(toSave.getNumber());

        Mockito.verify(seatAssembler).toModel(seatCaptor.capture());
        Seat captured = seatCaptor.getValue();

        assertEquals(expected, captured);
    }

    @Test
    void freeSeatWhenSeatNonexistent() {
        int number = 1;
        Mockito.when(seatRepository.findById(ArgumentMatchers.anyInt())).thenReturn(Optional.empty());

        SeatNotFoundException exception = assertThrows(SeatNotFoundException.class, () -> seatController.freeSeat(number));
        assertEquals("Seat not found, number: 1", exception.getMessage());
    }
}