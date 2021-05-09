package com.epam.training.ticketservice.domain.interfaces.impl;

import com.epam.training.ticketservice.domain.interfaces.Screening;

import java.util.Date;
import java.util.Objects;

public class SimpleScreening implements Screening {

    private final String title;
    private final String roomName;
    private final Date startTime;

    public SimpleScreening(String title, String roomName, Date startTime) {
        this.title = title;
        this.roomName = roomName;
        this.startTime = startTime;
    }

    public String getTitle() {
        return title;
    }

    public String getRoomName() {
        return roomName;
    }

    public Date getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleScreening that = (SimpleScreening) o;
        return title.equals(that.title) && roomName.equals(that.roomName) && startTime.equals(that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, roomName, startTime);
    }

    @Override
    public String toString() {
        return "SimpleScreening{" +
                "title='" + title + '\'' +
                ", roomName='" + roomName + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}
