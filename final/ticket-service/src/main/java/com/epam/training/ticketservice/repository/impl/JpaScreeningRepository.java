package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.ScreeningDao;
import com.epam.training.ticketservice.dataaccess.projection.ScreeningProjection;
import com.epam.training.ticketservice.domain.interfaces.Screening;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleScreening;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaScreeningRepository implements ScreeningRepository {

    private ScreeningDao screeningDao;

    public JpaScreeningRepository(ScreeningDao screeningDao) {
        this.screeningDao = screeningDao;
    }

    @Override
    public void saveScreening(Screening screening) {
        screeningDao.save(mapScreening(screening));
    }

    @Override
    public void removeScreening(Screening screeningToDelete) {
        screeningDao.deleteByTitleAndRoomNameAndStartTime(
                screeningToDelete.getTitle(),
                screeningToDelete.getRoomName(),
                screeningToDelete.getStartTime()
        );
    }

    @Override
    public List<Screening> getAllScreening() {
        List<ScreeningProjection> screeningProjections = screeningDao.findAll();
        return mapScreeningProjections(screeningProjections);
    }

    private ScreeningProjection mapScreening(Screening screening){
        return new ScreeningProjection(screening.getTitle(),screening.getRoomName(),
                screening.getStartTime());
    }

    private List<Screening> mapScreeningProjections(
            List<ScreeningProjection> screeningProjections) {
        return screeningProjections.stream()
                .map(this::mapScreeningProjection)
                .collect(Collectors.toList());
    }

    private Screening mapScreeningProjection(ScreeningProjection screeningProjection) {
        return new SimpleScreening(screeningProjection.getTitle(),
                screeningProjection.getRoomName(),
                screeningProjection.getStartTime());
    }

}
