package com.epam.training.ticketservice.dataaccess.projection;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MovieProjection that = (MovieProjection) o;
        return duration == that.duration && Objects.equals(id, that.id)
            && Objects.equals(title, that.title) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, genre, duration);
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "MovieProjection{"
            + "title='" + title + '\''
            + ", genre='" + genre + '\''
            + ", duration=" + duration
            + '}';
    }
}
