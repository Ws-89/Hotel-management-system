package pl.siuda.hotel.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IReservation {


    Set<Offert> getAvailability(AvailabilityRequest request);

    // throws exception
    Reservation makeReservation(ReservationRequest request);

    void cancelReservation(Long reservation_id);
}
