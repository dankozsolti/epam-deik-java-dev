package com.epam.ticketservice.repository;


import com.epam.training.ticketservice.dataaccess.dao.RoomDao;
import com.epam.training.ticketservice.dataaccess.dao.ScreeningDao;
import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import com.epam.training.ticketservice.dataaccess.projection.ScreeningProjection;
import com.epam.training.ticketservice.domain.interfaces.Room;
import com.epam.training.ticketservice.domain.interfaces.Screening;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleRoom;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleScreening;
import com.epam.training.ticketservice.repository.impl.JpaRoomRepository;
import com.epam.training.ticketservice.repository.impl.JpaScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class JpaScreeningRepositoryTest {

    private JpaScreeningRepository underTest;

    @Mock
    private ScreeningDao screeningDao;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new JpaScreeningRepository(screeningDao);
    }

    @Test
    public void testSaveScreeningShouldSaveSingleScreening() throws ParseException {
        //Given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Screening screening = new SimpleScreening("testMovie", "testRoom",
            simpleDateFormat.parse("2021-05-10 15:30"));
        ScreeningProjection screeningProjection = new ScreeningProjection(screening.getTitle(),
            screening.getRoomName(), screening.getStartTime());

        //When
        underTest.saveScreening(screening);

        //Then
        verify(screeningDao, times(1)).save(screeningProjection);
    }

    @Test
    public void testRemoveScreeningShouldRemoveSingleScreening() throws ParseException {
        //Given
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
        Screening screening = new SimpleScreening("testMovie", "testRoom",
            simpleDateFormat.parse("2021-05-10 15:30"));

        //When
        underTest.removeScreening(screening);

        //Then
        verify(screeningDao, times(1)).deleteByTitleAndRoomNameAndStartTime(
            screening.getTitle(), screening.getRoomName(), screening.getStartTime()
        );
    }

    @Test
    public void testListScreeningsShouldListAllScreenings() {
        //When
        underTest.getAllScreening();

        //Then
        verify(screeningDao, times(1)).findAll();
    }

}
