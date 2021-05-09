package com.epam.training.ticketservice.presentation.cli.handler;

import com.epam.training.ticketservice.presentation.cli.UserCommandAvailability;
import com.epam.training.ticketservice.service.RoomService;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;

@ShellComponent
public class RoomCommandHandler extends UserCommandAvailability {

    private RoomService roomService;

    public RoomCommandHandler(RoomService roomService) {
        this.roomService = roomService;
    }

    @ShellMethod(value = "Create given room", key="create room")
    @ShellMethodAvailability("isUserAdmin")
    public void createRoom(String roomName, String seatRowCount, String seatColCount) {
        roomService.createRoom(roomName,Integer.parseInt(seatRowCount),Integer.parseInt(seatColCount));
    }

    @ShellMethod(value = "Update given room", key="update room")
    @ShellMethodAvailability("isUserAdmin")
    public void updateRoom(String roomName, String seatRowCount, String seatColCount){
        roomService.updateRoom(roomName,Integer.parseInt(seatRowCount),Integer.parseInt(seatColCount));
    }

    @ShellMethod(value = "Delete given room", key="delete room")
    @ShellMethodAvailability("isUserAdmin")
    public void deleteRoom(String roomName){
        roomService.removeRoom(roomName);
    }

    @ShellMethod(value = "List all rooms", key="list rooms")
    public String listRooms(){
        return roomService.listRooms();
    }
}
