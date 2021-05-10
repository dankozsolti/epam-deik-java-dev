package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.dataaccess.projection.UserProjection;
import com.epam.training.ticketservice.exception.PasswordDoesntMatchException;
import com.epam.training.ticketservice.exception.UserNotFoundException;
import com.epam.training.ticketservice.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserService {

    private UserRepository userRepository;

    private UserProjection loggedInUser;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        loggedInUser = null;
    }

    public void login(String username, String password) throws UserNotFoundException, PasswordDoesntMatchException {
        UserProjection user = userRepository.getUserByUsername(username);
        if (Objects.isNull(user)) {
            throw new UserNotFoundException();
        }

        if (!user.getPassword().equals(password)) {
            throw new PasswordDoesntMatchException();
        }

        loggedInUser = user;
    }

    public UserProjection getLoggedInUser() {
        return this.loggedInUser;
    }

    public boolean isUserLoggedIn() {
        return !Objects.isNull(this.loggedInUser);
    }

    public boolean isUserAdmin() {
        return isUserLoggedIn() && this.loggedInUser.getRole().equals("ADMIN");
    }

    public void logout() {
        this.loggedInUser = null;
    }
}
