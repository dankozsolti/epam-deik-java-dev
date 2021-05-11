package com.epam.training.ticketservice.dataaccess.dao;

import com.epam.training.ticketservice.dataaccess.projection.RoomProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface RoomDao extends JpaRepository<RoomProjection, UUID> {
    Optional<RoomProjection> findByRoomName(String roomName);

    void deleteByRoomName(String roomName);
}
