package com.epam.ticketservice.service;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Screening;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleScreening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Arrays;


public class ScreeningServiceTest {

    private ScreeningService underTest;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private ScreeningRepository screeningRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningService(movieRepository, screeningRepository);
    }

    @Test
    public void testCreateScreeningShouldCreateSingleScreening() throws ParseException {
        //Given
        Screening screening = new SimpleScreening("testMovie", "testRoom", underTest.toDate("2021-05-10 15:30"));

        //When
        underTest.createScreening(screening.getTitle(), screening.getRoomName(), screening.getStartTime());

        //Then
        verify(screeningRepository, times(1)).saveScreening(screening);
    }

    @Test
    public void testRemoveScreeningShouldRemoveSingleScreening() throws ParseException {
        //Given
        Screening screening = new SimpleScreening("testMovie", "testRoom", underTest.toDate("2021-05-10 15:30"));
        given(screeningRepository.getAllScreening()).willReturn(Arrays.asList(screening));

        //When
        underTest.removeScreening(screening.getTitle(), screening.getRoomName(), screening.getStartTime());

        //Then
        verify(screeningRepository, times(1)).removeScreening(screening);
    }

    @Test
    public void testListScreeningsShouldListAllScreenings() {
        //When
        underTest.listScreenings();

        //Then
        verify(screeningRepository, times(1)).getAllScreening();
    }

}
