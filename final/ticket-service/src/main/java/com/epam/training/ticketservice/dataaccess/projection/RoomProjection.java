package com.epam.training.ticketservice.dataaccess.projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class RoomProjection {

    @Id
    @GeneratedValue
    private UUID id;
    private String roomName;
    private int seatRowCount;
    private int seatColCount;


    public RoomProjection(String roomName, int seatRowCount, int seatColCount) {
        this.roomName = roomName;
        this.seatRowCount = seatRowCount;
        this.seatColCount = seatColCount;
    }

    protected RoomProjection() {

    }


    public String getRoomName() {
        return roomName;
    }

    public int getSeatRowCount() {
        return seatRowCount;
    }

    public int getSeatColCount() {
        return seatColCount;
    }
}
