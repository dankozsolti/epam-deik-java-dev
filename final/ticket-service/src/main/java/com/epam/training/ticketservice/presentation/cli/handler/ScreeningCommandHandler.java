package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.ScreeningService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

import java.text.ParseException;

@ShellComponent
public class ScreeningCommandHandler extends UserCommandAvailability {

    private ScreeningService screeningService;

    public ScreeningCommandHandler(ScreeningService screeningService) {
        this.screeningService = screeningService;
    }

    @ShellMethod(value = "Create given screening", key="create screening")
    @ShellMethodAvailability("isUserAdmin")
    public String createScreening(String title, String room, String startTime) throws ParseException {
        return screeningService.createScreening(title,room, screeningService.toDate(startTime));
    }

    @ShellMethod(value = "Delete given screening", key="delete screening")
    @ShellMethodAvailability("isUserAdmin")
    public void deleteScreening(String title, String room, String startTime) throws ParseException {
        screeningService.removeScreening(title,room,screeningService.toDate(startTime));
    }

    @ShellMethod(value = "List all screenings", key="list screenings")
    public String listScreenings(){
        return screeningService.listScreenings();
    }
}
