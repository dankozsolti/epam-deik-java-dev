package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.MovieService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class MovieCommandHandler extends UserCommandAvailability {

    private MovieService movieService;

    public MovieCommandHandler(MovieService movieService) {
        this.movieService = movieService;
    }

    @ShellMethod(value = "Create given movie", key = "create movie")
    @ShellMethodAvailability("isUserAdmin")
    public void createMovie(String title, String genre, String duration) {
        movieService.createMovie(title, genre, Integer.parseInt(duration));
    }

    @ShellMethod(value = "Update given movie", key = "update movie")
    @ShellMethodAvailability("isUserAdmin")
    public void updateMovie(String title, String genre, String duration) {
        movieService.updateMovie(title, genre, Integer.parseInt(duration));
    }

    @ShellMethod(value = "Delete given movie", key = "delete movie")
    @ShellMethodAvailability("isUserAdmin")
    public void deleteMovie(String title) {
        movieService.removeMovie(title);
    }

    @ShellMethod(value = "List all movies", key = "list movies")
    public String listMovies() {
        return movieService.listMovies();
    }
}
