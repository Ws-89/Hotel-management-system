package pl.siuda.hotel.reservation;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface ReservationServiceInterface {


    List<Availability> getAvailability(String location, LocalDate from, LocalDate to, Integer partySize);

    // throws exception
    Reservation makeReservation(Integer roomId, LocalDate from, LocalDate to, Integer partySize, Integer guestId);

    void cancelReservation();
}
