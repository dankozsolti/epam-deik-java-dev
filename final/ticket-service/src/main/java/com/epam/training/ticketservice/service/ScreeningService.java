package com.epam.training.ticketservice.service;

import com.epam.training.ticketservice.domain.interfaces.Movie;
import com.epam.training.ticketservice.domain.interfaces.Screening;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleMovie;
import com.epam.training.ticketservice.domain.interfaces.impl.SimpleScreening;
import com.epam.training.ticketservice.repository.MovieRepository;
import com.epam.training.ticketservice.repository.RoomRepository;
import com.epam.training.ticketservice.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ScreeningService {

    String timePattern = "yyyy-MM-dd kk:mm";

    private MovieRepository movieRepository;
    private ScreeningRepository screeningRepository;


    public ScreeningService(MovieRepository movieRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    public String createScreening(String title, String roomName, Date startTime) {
        Screening screening = new SimpleScreening(title, roomName, startTime);
        switch (isOverlapping(roomName, startTime)) {
            case 0:
                screeningRepository.saveScreening(screening);
                return null;
            case 1:
                return "There is an overlapping screening";
            case 2:
                return "This would start in the break period after another screening in this room";
            default:
                return null;
        }
    }

    public void removeScreening(String title, String roomName, Date startTime) {
        Screening screeningToRemove = new SimpleScreening(title, roomName, startTime);
        screeningRepository.removeScreening(screeningToRemove);
    }

    public String listScreenings() {
        List<Screening> screenings = screeningRepository.getAllScreening();
        StringBuilder screeningsString = new StringBuilder();

        if (screenings.isEmpty()) {
            screeningsString.append("There are no screenings");
        } else {
            for (Screening s : screenings) {
                Movie m = getMatchingMovie(s.getTitle());

                screeningsString.append(s.getTitle()).append(" (")
                    .append(m.getGenre()).append(", ").append(m.getDuration())
                    .append(" minutes), screened in room ").append(s.getRoomName())
                    .append(", at ").append(formatDate(s.getStartTime())).append("\n");
            }
        }
        return screeningsString.toString().trim();
    }

    private int isOverlapping(String roomName, Date startTime) {
        List<Screening> screenings = screeningRepository.getAllScreening();
        for (Screening s : screenings) {
            Date startOfMovie = s.getStartTime();
            Date endOfMovie = new Date(startOfMovie.getTime()
                + (1000 * 60)
                * getMatchingMovie(s.getTitle()).getDuration());
            Date endOfBreak = new Date(endOfMovie.getTime() + (1000 * 60 * 10));
            if (startTime.getTime() >= startOfMovie.getTime()
                && startTime.getTime() < endOfMovie.getTime()
                && s.getRoomName().equals(roomName)) {
                return 1;
            } else if (startTime.getTime() >= endOfMovie.getTime()
                && startTime.getTime() <= endOfBreak.getTime()
                && s.getRoomName().equals(roomName)) {
                return 2;
            }
        }
        return 0;
    }

    private Movie getMatchingMovie(String s) {
        return movieRepository.getAllMovie().stream()
            .filter(movie -> movie.getTitle().equals(s))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("No title found"));
    }

    private String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        return simpleDateFormat.format(date);
    }

    public Date toDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
        return simpleDateFormat.parse(s);
    }
}
