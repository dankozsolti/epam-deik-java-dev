package com.epam.training.ticketservice.dataaccess.projection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
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
}
