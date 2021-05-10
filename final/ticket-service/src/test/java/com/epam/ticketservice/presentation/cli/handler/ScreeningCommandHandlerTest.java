package com.epam.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.Screening;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleScreening;
import com.epam.training.ticketservice.presentation.cli.handler.MovieCommandHandler;
import com.epam.training.ticketservice.presentation.cli.handler.RoomCommandHandler;
import com.epam.training.ticketservice.presentation.cli.handler.ScreeningCommandHandler;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.service.MovieService;
import com.epam.training.ticketservice.service.RoomService;
import com.epam.training.ticketservice.service.ScreeningService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class ScreeningCommandHandlerTest {

    private ScreeningCommandHandler underTest;

    @Mock
    private ScreeningService screeningService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ScreeningCommandHandler(screeningService);
    }

    @Test
    public void testListServicesShouldReturnListOfServices() {
        //When
        underTest.listScreenings();

        //Then
        verify(screeningService, times(1)).listScreenings();
    }

}

