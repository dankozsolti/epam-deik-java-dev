package com.epam.training.ticketservice.dataaccess.dao;

import com.epam.training.ticketservice.dataaccess.projection.MovieProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Transactional
public interface MovieDao extends JpaRepository<MovieProjection, UUID> {
    void deleteByTitle(String title);

    Optional<MovieProjection> findByTitle(String title);
}
