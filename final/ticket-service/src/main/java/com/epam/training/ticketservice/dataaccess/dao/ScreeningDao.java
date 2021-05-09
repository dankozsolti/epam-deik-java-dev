package com.epam.training.ticketservice.dataaccess.dao;

import com.epam.training.ticketservice.dataaccess.projection.ScreeningProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Transactional
public interface ScreeningDao extends JpaRepository<ScreeningProjection, UUID> {
    void deleteByTitleAndRoomNameAndStartTime(String title, String roomName, Date startTime);
}
