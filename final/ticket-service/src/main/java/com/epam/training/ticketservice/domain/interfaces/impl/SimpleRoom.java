package com.epam.training.ticketservice.domain.interfaces.impl;

import com.epam.training.ticketservice.domain.interfaces.Room;

import java.util.Objects;

public class SimpleRoom implements Room {

    private final String roomName;
    private final int seatRowCount;
    private final int seatColCount;

    public SimpleRoom(String roomName, int seatRowCount, int seatColCount) {
        this.roomName = roomName;
        this.seatRowCount = seatRowCount;
        this.seatColCount = seatColCount;
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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleRoom that = (SimpleRoom) o;
        return seatRowCount == that.seatRowCount && seatColCount == that.seatColCount && roomName.equals(that.roomName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomName, seatRowCount, seatColCount);
    }

    @Override
    public String toString() {
        return "SimpleRoom{" +
                "roomName='" + roomName + '\'' +
                ", seatRowCount=" + seatRowCount +
                ", seatColCount=" + seatColCount +
                '}';
    }
}
