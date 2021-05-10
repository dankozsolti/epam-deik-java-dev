package com.epam.training.ticketservice.dataaccess.projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
public class ScreeningProjection {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String roomName;
    private Date startTime;


    public ScreeningProjection(String title, String roomName, Date startTime) {
        this.title = title;
        this.roomName = roomName;
        this.startTime = startTime;
    }

    protected ScreeningProjection() {

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
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScreeningProjection that = (ScreeningProjection) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title)
            && Objects.equals(roomName, that.roomName) && Objects.equals(startTime, that.startTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, roomName, startTime);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
