package com.epam.ticketservice.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class RoomServiceTest {

    private RoomService underTest;

    @Mock
    private RoomRepository roomRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new RoomService(roomRepository);
    }

    @Test
    public void testCreateRoomShouldCreateSingleRoom() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);

        //When
        underTest.createRoom(room.getRoomName(), room.getSeatRowCount(), room.getSeatColCount());

        //Then
        verify(roomRepository, times(1)).saveRoom(room);
    }

    @Test
    public void testRemoveRoomShouldRemoveSingleRoom() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);
        given(roomRepository.getAllRoom()).willReturn(Arrays.asList(room));

        //When
        underTest.removeRoom(room.getRoomName());

        //Then
        verify(roomRepository, times(1)).removeRoom(room);
    }

    @Test
    public void testListRoomsShouldListAllRooms() {
        //When
        underTest.listRooms();

        //Then
        verify(roomRepository, times(1)).getAllRoom();
    }

    @Test
    public void testUpdateRoomShouldUpdateSingleRoom() {
        //Given
        Room oldRoom = new SimpleRoom("testRoom", 12, 10);
        Room newRoom = new SimpleRoom("testRoom", 12, 10);
        underTest.createRoom(oldRoom.getRoomName(), oldRoom.getSeatRowCount(), oldRoom.getSeatColCount());
        given(roomRepository.getAllRoom()).willReturn(Arrays.asList(oldRoom));

        //When
        underTest.updateRoom(newRoom.getRoomName(), newRoom.getSeatRowCount(), newRoom.getSeatColCount());

        //Then
        verify(roomRepository, times(1)).updateRoom(oldRoom, newRoom);
    }

}
