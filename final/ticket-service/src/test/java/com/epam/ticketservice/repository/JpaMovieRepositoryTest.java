package com.epam.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.dao.MovieDao;
import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.repository.impl.JpaMovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JpaMovieRepositoryTest {

    private JpaMovieRepository underTest;

    @Mock
    private MovieDao movieDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaMovieRepository(movieDao);
    }

    @Test
    public void testSaveMovieShouldSaveSingleMovie() {
        //Given
        Movie movie = new SimpleMovie("testMovie", "testGenre", 90);
        MovieProjection movieProjection = new MovieProjection(movie.getTitle(), movie.getGenre(), movie.getDuration());

        //When
        underTest.saveMovie(movie);

        //Then
        verify(movieDao, times(1)).save(movieProjection);
    }

    @Test
    public void testRemoveMovieShouldRemoveSingleMovie() {
        //Given
        Movie movie = new SimpleMovie("testMovie", "testGenre", 90);

        //When
        underTest.removeMovie(movie);

        //Then
        verify(movieDao, times(1)).deleteByTitle(movie.getTitle());
    }

    @Test
    public void testListMoviesShouldListAllMovies() {
        //When
        underTest.getAllMovie();

        //Then
        verify(movieDao, times(1)).findAll();
    }

    /*@Test
    public void testUpdateMovieShouldUpdateSingleMovie() {
        //Given
        Movie oldMovie = new SimpleMovie("testMovie", "testGenre", 90);
        Movie newMovie = new SimpleMovie("testMovie", "testGenre2", 180);
        MovieProjection movieProjection = new MovieProjection(oldMovie.getTitle(),
            oldMovie.getGenre(),oldMovie.getDuration());

        //When
        underTest.updateMovie(oldMovie,newMovie);

        //Then
        movieProjection.setGenre(newMovie.getGenre());
        movieProjection.setDuration(newMovie.getDuration());
        verify(movieDao,times(1)).save(movieProjection);
    }*/

}
