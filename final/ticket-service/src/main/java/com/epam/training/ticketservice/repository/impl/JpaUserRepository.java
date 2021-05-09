package com.epam.training.ticketservice.repository.impl;

import com.epam.training.ticketservice.dataaccess.dao.UserDao;
import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {
    @Autowired
    private UserDao userDao;

    @Override
    public UserProjection getUserByUsername(String username) {
        Optional<UserProjection> user = userDao.findByUsername(username);
        if (!user.isPresent())
            return null;
        return user.get();
    }
}
