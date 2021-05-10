package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository {
    void saveMovie(Movie movie);

    List<Movie> getAllMovie();

    void removeMovie(Movie movieToDelete);

    void updateMovie(Movie oldMovie, Movie newMovie);
}
