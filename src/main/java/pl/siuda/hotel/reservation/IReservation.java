package pl.siuda.hotel.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IReservation {


    Set<Availability> getAvailability(AvailabilityRequest request);

    // throws exception
    List<Reservation> makeAReservation(List<ReservationRequest> request);

    boolean cancelReservation(Long reservation_id);
}
