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

    String TIME_PATTERN = "yyyy-MM-dd kk:mm";

    private MovieRepository movieRepository;
    private ScreeningRepository screeningRepository;


    public ScreeningService(MovieRepository movieRepository, ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.screeningRepository = screeningRepository;
    }

    public String createScreening(String title, String roomName, Date startTime) {
        Screening screening = new SimpleScreening(title, roomName, startTime);
        if (isOverlappingMovie(roomName,startTime)) {
            return "There is an overlapping screening";
        } else if (isOverlappingBreak(roomName,startTime)) {
            return "This would start in the break period after another screening in this room";
        } else {
            screeningRepository.saveScreening(screening);
            return "";
        }


    }

    public void removeScreening(String title, String roomName, Date startTime) {
        Screening screeningToRemove = new SimpleScreening(title,roomName,startTime);
        screeningRepository.removeScreening(screeningToRemove);
    }

    public String listScreenings() {
        List<Screening> screenings = screeningRepository.getAllScreening();
        StringBuilder screeningsString = new StringBuilder();

        if(screenings.isEmpty()) {
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

    private boolean isOverlappingMovie(String roomName, Date startTime) {
        List<Screening> screenings = screeningRepository.getAllScreening();
        for (Screening s : screenings) {
            Date start = s.getStartTime();
            Date end = new Date(start.getTime()
                    + (1000 * 60)
                    * getMatchingMovie(s.getTitle()).getDuration());

            if (startTime.getTime() >= start.getTime() && startTime.getTime() <= end.getTime()
                    && s.getRoomName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    private boolean isOverlappingBreak(String roomName, Date startTime) {
        List<Screening> screenings = screeningRepository.getAllScreening();
        for (Screening s : screenings) {
            Date startOfMovie = s.getStartTime();
            Date startOfBreak = new Date(startOfMovie.getTime()
                    + (1000 * 60)
                    * getMatchingMovie(s.getTitle()).getDuration());
            Date endOfBreak = new Date(startOfBreak.getTime() + (1000*60*10));

            if (startTime.getTime() >= startOfBreak.getTime() && startTime.getTime() <= endOfBreak.getTime()
                    && s.getRoomName().equals(roomName)) {
                return true;
            }
        }
        return false;
    }

    private Movie getMatchingMovie(String s){
        return movieRepository.getAllMovie().stream()
                .filter(movie -> movie.getTitle().equals(s))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No title found"));
    }

    private String formatDate(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN);
        return simpleDateFormat.format(date);
    }

    public Date toDate(String s) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_PATTERN);
        return simpleDateFormat.parse(s);
    }
}
