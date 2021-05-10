package com.epam.training.ticketservice.dataaccess.projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RoomProjection that = (RoomProjection) o;
        return seatRowCount == that.seatRowCount && seatColCount == that.seatColCount
            && Objects.equals(id, that.id) && Objects.equals(roomName, that.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomName, seatRowCount, seatColCount);
    }


    public void setSeatRowCount(int seatRowCount) {
        this.seatRowCount = seatRowCount;
    }

    public void setSeatColCount(int seatColCount) {
        this.seatColCount = seatColCount;
    }
}
