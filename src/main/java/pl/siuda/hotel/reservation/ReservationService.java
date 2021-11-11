package pl.siuda.hotel.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import pl.siuda.hotel.room.RoomService;

import java.time.LocalDate;
import java.util.List;

public class ReservationService implements ReservationServiceInterface {

    @Autowired
    private final RoomService roomService;

    public ReservationService(RoomService roomService) {
        this.roomService = roomService;
    }


    @Override
    public List<Availability> getAvailability(String location, LocalDate from, LocalDate to, Integer partySize) {


        return null;
    }

    @Override
    public Reservation makeReservation(Integer roomId, LocalDate from, LocalDate to, Integer partySize, Integer guestId) {
        return null;
    }

    @Override
    public void cancelReservation() {

    }
}
