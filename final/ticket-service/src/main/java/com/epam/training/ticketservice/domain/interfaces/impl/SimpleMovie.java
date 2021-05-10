package com.epam.training.ticketservice.domain.interfaces.impl;

import com.epam.training.ticketservice.domain.interfaces.Movie;

import java.util.Objects;

public class SimpleMovie implements Movie {

    private final String title;
    private final String genre;
    private final int duration;

    public SimpleMovie(String title, String genre, int duration) {
        this.title = title;
        this.genre = genre;
        this.duration = duration;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
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
        SimpleMovie that = (SimpleMovie) o;
        return duration == that.duration && Objects.equals(title, that.title) && Objects.equals(genre, that.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, genre, duration);
    }

    @Override
    public String toString() {
        return "SimpleMovie{"
            + "title='" + title + '\''
            + ", genre='" + genre + '\''
            + ", duration=" + duration
            + '}';
    }
}
