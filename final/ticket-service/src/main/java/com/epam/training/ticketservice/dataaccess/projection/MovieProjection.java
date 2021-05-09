package com.epam.training.ticketservice.dataaccess.projection;

import com.epam.training.ticketservice.domain.interfaces.Movie;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
public class MovieProjection {

    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String genre;
    private int duration;

    public MovieProjection(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    protected MovieProjection() {

    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getDuration() {
        return duration;
    }

}
