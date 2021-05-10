package com.epam.training.ticketservice.presentation.cli;

import com.epam.training.ticketservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.stereotype.Component;

@Component
public class UserCommandAvailability {

    @Autowired
    private UserService userService;

    public Availability isUserSignedIn() {
        if (!userService.isUserLoggedIn()) {
            return Availability.unavailable("you are not signed in. Please sign in to use this command");
        }
        return Availability.available();
    }

    public Availability isUserSignedOut() {
        if (userService.isUserLoggedIn()) {
            return Availability.unavailable("you are signed in. Please sign out to use this command");
        }
        return Availability.available();
    }

    public Availability isUserAdmin() {
        if (!userService.isUserLoggedIn()) {
            return Availability.unavailable("you are not signed in. Please sign in to use this command");
        }
        if (!userService.isUserAdmin()) {
            return Availability.unavailable("you don't have enough permission to use this command.");
        }
        return Availability.available();
    }

}
