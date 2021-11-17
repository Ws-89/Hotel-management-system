package pl.siuda.hotel.reservation;

import pl.siuda.hotel.room.Room;
import pl.siuda.hotel.room.RoomService;

import java.util.List;

public class AvailiabilityService {

    private final RoomService roomService;

    public AvailiabilityService(RoomService roomService) {
        this.roomService = roomService;
    }

    public List<Room> availableRooms(AvailabilityRequest availabilityRequest){
        return null;
    }
}
