package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public void createRoom(String roomName, int seatRowNum, int seatColNum) {
        Room room = new SimpleRoom(roomName, seatRowNum, seatColNum);
        if (roomRepository.getAllRoom().stream()
            .anyMatch(roomToCreate -> roomToCreate.getRoomName().equals(roomName))) {
            throw new IllegalArgumentException("Room already exists");
        }
        roomRepository.saveRoom(room);
    }

    public void removeRoom(String roomName) {
        Room roomToRemove = roomRepository.getAllRoom().stream()
            .filter(room -> room.getRoomName().equals(roomName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown room"));
        roomRepository.removeRoom(roomToRemove);
    }

    public void updateRoom(String roomName, int seatRowNum, int seatColNum) {
        Room roomToUpdate = roomRepository.getAllRoom().stream()
            .filter(room -> room.getRoomName().equals(roomName))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown room"));
        roomRepository.updateRoom(roomToUpdate, new SimpleRoom(roomName, seatRowNum, seatColNum));
    }

    public String listRooms() {
        List<Room> rooms = roomRepository.getAllRoom();
        StringBuilder roomString = new StringBuilder();
        if (rooms.isEmpty()) {
            roomString.append("There are no rooms at the moment");
        } else {
            for (Room r : rooms) {
                roomString.append("Room ").append(r.getRoomName()).append(" with ")
                    .append(r.getSeatRowCount() * r.getSeatColCount()).append(" seats, ")
                    .append(r.getSeatRowCount()).append(" rows and ")
                    .append(r.getSeatColCount()).append(" columns\n");
            }
        }
        return roomString.toString().trim();
    }

}
