package com.epam.ticketservice.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

public class MovieServiceTest {

    private MovieService underTest;

    @Mock
    private MovieRepository movieRepository;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieService(movieRepository);
    }

    @Test
    public void testCreateMovieShouldCreateSingleMovie() {
        //Given
        Movie movie = new SimpleMovie("testMovie", "testGenre", 90);

        //When
        underTest.createMovie(movie.getTitle(), movie.getGenre(), movie.getDuration());

        //Then
        verify(movieRepository, times(1)).saveMovie(movie);
    }

    @Test
    public void testRemoveMovieShouldDeleteSingleMovie() {
        //Given
        Movie movie = new SimpleMovie("testMovie4", "testGenre", 90);
        underTest.createMovie(movie.getTitle(), movie.getGenre(), movie.getDuration());
        given(movieRepository.getAllMovie()).willReturn(Arrays.asList(movie));

        //When
        underTest.removeMovie(movie.getTitle());

        //Then
        verify(movieRepository, times(1)).removeMovie(movie);
    }

    @Test
    public void testListMoviesShouldListAllMovies() {
        //When
        underTest.listMovies();

        //Then
        verify(movieRepository, times(1)).getAllMovie();
    }

    @Test
    public void testUpdateMovieShouldUpdateSingleMovie() {
        //Given
        Movie oldMovie = new SimpleMovie("testMovie5", "testGenre", 90);
        Movie newMovie = new SimpleMovie("testMovie5", "testGenre2", 180);
        underTest.createMovie(oldMovie.getTitle(), oldMovie.getGenre(), oldMovie.getDuration());
        given(movieRepository.getAllMovie()).willReturn(Arrays.asList(oldMovie));

        //When
        underTest.updateMovie(newMovie.getTitle(), newMovie.getGenre(), newMovie.getDuration());

        //Then
        verify(movieRepository, times(1)).updateMovie(oldMovie, newMovie);
    }

}
