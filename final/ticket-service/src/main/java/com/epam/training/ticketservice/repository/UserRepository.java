package com.epam.training.ticketservice.repository;

import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {
    UserProjection getUserByUsername(String username);
}
