package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.domain.interfaces.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository {
    void saveRoom(Room room);

    List<Room> getAllRoom();

    void removeRoom(Room roomToDelete);

    void updateRoom(Room oldRoom, Room newRoom);
}
