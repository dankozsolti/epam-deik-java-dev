package com.epam.ticketservice.repository;


import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.repository.impl.JpaRoomRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JpaRoomRepositoryTest {

    private JpaRoomRepository underTest;

    @Mock
    private RoomDao roomDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaRoomRepository(roomDao);
    }

    @Test
    public void testSaveRoomShouldSaveSingleRoom() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);
        RoomProjection roomProjection = new RoomProjection(room.getRoomName(),
            room.getSeatRowCount(), room.getSeatColCount());

        //When
        underTest.saveRoom(room);

        //Then
        verify(roomDao, times(1)).save(roomProjection);
    }

    @Test
    public void testRemoveRoomShouldRemoveSingleRoom() {
        //Given
        Room room = new SimpleRoom("testRoom", 12, 10);

        //When
        underTest.removeRoom(room);

        //Then
        verify(roomDao, times(1)).deleteByRoomName(room.getRoomName());
    }

    @Test
    public void testListRoomsShouldListAllRooms() {
        //When
        underTest.getAllRoom();

        //Then
        verify(roomDao, times(1)).findAll();
    }

}
