package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaRoomRepository implements RoomRepository {

    private RoomDao roomDao;

    @Autowired
    public JpaRoomRepository(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void saveRoom(Room room) {
        roomDao.save(mapRoom(room));
    }

    @Override
    public List<Room> getAllRoom() {
        List<RoomProjection> roomProjections = roomDao.findAll();
        return mapRoomProjections(roomProjections);
    }

    @Override
    public void removeRoom(Room roomToDelete) {
        roomDao.deleteByRoomName(roomToDelete.getRoomName());
    }

    private RoomProjection findRoom(Room room) {
        Optional<RoomProjection> roomProjection =
            roomDao.findByRoomName(room.getRoomName());
        if (!roomProjection.isPresent()) {
            return null;
        }
        return roomProjection.get();
    }

    @Override
    public void updateRoom(Room oldRoom, Room newRoom) {
        RoomProjection roomProjection = findRoom(oldRoom);
        roomProjection.setSeatRowCount(newRoom.getSeatRowCount());
        roomProjection.setSeatColCount(newRoom.getSeatColCount());

        roomDao.save(roomProjection);
    }

    private RoomProjection mapRoom(Room room) {
        return new RoomProjection(room.getRoomName(), room.getSeatRowCount(), room.getSeatColCount());
    }

    private List<Room> mapRoomProjections(List<RoomProjection> roomProjections) {
        return roomProjections.stream()
            .map(this::mapRoomProjection)
            .collect(Collectors.toList());
    }

    private Room mapRoomProjection(RoomProjection roomProjection) {
        return new SimpleRoom(roomProjection.getRoomName(), roomProjection.getSeatRowCount(),
            roomProjection.getSeatColCount());
    }
}
