package com.epam.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.presentation.cli.handler.MovieCommandHandler;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieCommandHandlerTest {

    private MovieCommandHandler underTest;

    @Mock
    private MovieService movieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new MovieCommandHandler(movieService);
    }

    @Test
    public void testCreateMovieShouldCreateSingleMovie() {
        //Given
        Movie movie = new SimpleMovie("testMovie", "testGenre", 90);

        //When
        underTest.createMovie(movie.getTitle(),movie.getGenre(),String.valueOf(movie.getDuration()));

        //Then
        verify(movieService,times(1)).createMovie(movie.getTitle(),
            movie.getGenre(),movie.getDuration());

    }

    @Test
    public void testDeleteMovieShouldDeleteSingleMovieByTitle() {
        //Given
        Movie movie = new SimpleMovie("testMovie", "testGenre", 90);

        //When
        underTest.deleteMovie(movie.getTitle());

        //Then
        verify(movieService,times(1)).removeMovie(movie.getTitle());
    }

    @Test
    public void testUpdateMovieShouldUpdateSingleMovie(){
        //Given
        Movie oldMovie = new SimpleMovie("testMovie", "testGenre", 90);
        Movie newMovie = new SimpleMovie("testMovie", "testGenre2", 180);

        //When
        underTest.updateMovie(oldMovie.getTitle(),newMovie.getGenre(),String.valueOf(newMovie.getDuration()));

        //Then
        verify(movieService,times(1)).updateMovie(oldMovie.getTitle(),
            newMovie.getGenre(),newMovie.getDuration());
    }

    @Test
    public void testListMoviesShouldReturnListOfMovies(){
        //When
        underTest.listMovies();

        //Then
        verify(movieService,times(1)).listMovies();
    }

}
