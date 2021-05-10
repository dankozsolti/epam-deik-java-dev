package com.epam.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.presentation.cli.handler.MovieCommandHandler;
import com.epam.training.ticketservice.presentation.cli.handler.RoomCommandHandler;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RoomCommandHandlerTest {

    private RoomCommandHandler underTest;

    @Mock
    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomCommandHandler(roomService);
    }

    @Test
    public void testCreateRoomShouldCreateSingleRoom() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);

        //When
        underTest.createRoom(room.getRoomName(),String.valueOf(room.getSeatRowCount()),
            String.valueOf(room.getSeatColCount()));

        //Then
        verify(roomService,times(1)).createRoom(room.getRoomName(),room.getSeatRowCount(),
            room.getSeatColCount());

    }

    @Test
    public void testDeleteRoomShouldDeleteSingleRoomByRoomName() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);

        //When
        underTest.deleteRoom(room.getRoomName());

        //Then
        verify(roomService,times(1)).removeRoom(room.getRoomName());
    }

    @Test
    public void testUpdateRoomShouldUpdateSingleRoom(){
        //Given
        Room oldRoom = new SimpleRoom("testRoom", 12, 10);
        Room newRoom = new SimpleRoom("testRoom", 20, 22);

        //When
        underTest.updateRoom(oldRoom.getRoomName(),String.valueOf(newRoom.getSeatRowCount()),
            String.valueOf(newRoom.getSeatColCount()));

        //Then
        verify(roomService,times(1)).updateRoom(oldRoom.getRoomName(),
            newRoom.getSeatRowCount(),newRoom.getSeatColCount());
    }

    @Test
    public void testListRoomsShouldReturnListOfRooms(){
        //When
        underTest.listRooms();

        //Then
        verify(roomService,times(1)).listRooms();
    }

}

