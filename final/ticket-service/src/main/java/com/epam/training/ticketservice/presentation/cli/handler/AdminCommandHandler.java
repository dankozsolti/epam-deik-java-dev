package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.exception.PasswordDoesntMatchException;
import com.epam.training.ticketservice.exception.UserNotFoundException;
import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.UserService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

/**
 * Command handler for admin commands
 */
@ShellComponent
public class AdminCommandHandler extends UserCommandAvailability {

    private UserService userService;

    public AdminCommandHandler(UserService userService) {
        this.userService = userService;
    }

    @ShellMethod(value = "Signs in privileged account", key = "sign in privileged")
    @ShellMethodAvailability("isUserSignedOut")
    public String signInPrivileged(String username, String password) {
        try {
            userService.login(username, password);
        } catch (UserNotFoundException | PasswordDoesntMatchException e) {
            return "Login failed due to incorrect credentials";
        }
        return "";
    }

    @ShellMethod(value = "Describes the signed in account", key = "describe account")
    public String describeAccount() {
        if(!userService.isUserLoggedIn()){
            return "You are not signed in";
        }
        return "Signed in with privileged account '" + userService.getLoggedInUser().getUsername()+"'";
    }

    @ShellMethod(value = "Signs out of the current account", key = "sign out")
    @ShellMethodAvailability("isUserSignedIn")
    public String signOut() {
        userService.logout();
        return "Signing out";
    }
}