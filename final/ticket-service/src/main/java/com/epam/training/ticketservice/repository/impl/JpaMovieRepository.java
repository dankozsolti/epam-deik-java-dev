package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class JpaMovieRepository implements MovieRepository {

    private MovieDao movieDao;

    @Autowired
    public JpaMovieRepository(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    public void saveMovie(Movie movie) {
        movieDao.save(mapMovie(movie));
    }

    protected MovieProjection mapMovie(Movie movie) {
        return new MovieProjection(movie.getTitle(), movie.getGenre(), movie.getDuration());
    }

    private MovieProjection findMovie(Movie movie) {
        Optional<MovieProjection> movieProjection =
            movieDao.findByTitle(movie.getTitle());
        if (!movieProjection.isPresent()) {
            return null;
        }
        return movieProjection.get();
    }

    @Override
    public List<Movie> getAllMovie() {
        List<MovieProjection> movieProjections = movieDao.findAll();
        return mapMovieProjections(movieProjections);
    }

    @Override
    public void removeMovie(Movie movieToDelete) {
        movieDao.deleteByTitle(movieToDelete.getTitle());
    }

    @Override
    public void updateMovie(Movie oldMovie, Movie newMovie) {
        MovieProjection movieProjection = findMovie(oldMovie);
        movieProjection.setGenre(newMovie.getGenre());
        movieProjection.setDuration(newMovie.getDuration());
        movieDao.save(movieProjection);
    }

    private List<Movie> mapMovieProjections(List<MovieProjection> movieProjections) {
        return movieProjections.stream()
            .map(this::mapMovieProjection)
            .collect(Collectors.toList());
    }

    private Movie mapMovieProjection(MovieProjection movieProjection) {
        return new SimpleMovie(movieProjection.getTitle(), movieProjection.getGenre(), movieProjection.getDuration());
    }
}
