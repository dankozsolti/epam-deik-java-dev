package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void createMovie(String title, String genre, int duration) {
        Movie movie = new SimpleMovie(title, genre, duration);
        if (movieRepository.getAllMovie().stream()
            .anyMatch(movieToCreate -> movieToCreate.getTitle().equals(title))) {
            throw new IllegalArgumentException("Movie already exists");
        }
        movieRepository.saveMovie(movie);
    }

    public void removeMovie(String title) {
        Movie movieToRemove = movieRepository.getAllMovie().stream()
            .filter(movie -> movie.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown movie"));
        movieRepository.removeMovie(movieToRemove);
    }

    public void updateMovie(String title, String genre, int duration) {
        Movie movieToUpdate = movieRepository.getAllMovie().stream()
            .filter(movie -> movie.getTitle().equals(title))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Unknown movie"));
        movieRepository.updateMovie(movieToUpdate, new SimpleMovie(title, genre, duration));
    }

    public String listMovies() {
        List<Movie> movies = movieRepository.getAllMovie();
        StringBuilder moviesString = new StringBuilder();
        if (movies.isEmpty()) {
            moviesString.append("There are no movies at the moment");
        } else {
            for (Movie m : movies) {
                moviesString.append(m.getTitle()).append(" (").append(m.getGenre()).append(", ")
                    .append(m.getDuration()).append(" minutes)\n");
            }
        }
        return moviesString.toString().trim();
    }
}
