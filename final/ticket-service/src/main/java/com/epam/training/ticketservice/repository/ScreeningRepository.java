package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.domain.interfaces.Screening;

import java.util.List;

public interface ScreeningRepository {
    void saveScreening(Screening screening);

    void removeScreening(Screening screeningToDelete);

    List<Screening> getAllScreening();

}
