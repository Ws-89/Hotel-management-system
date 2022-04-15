package pl.siuda.hotel.reservation.availabilityCart;

import pl.siuda.hotel.reservation.Availability;

import java.util.HashSet;
import java.util.Set;

public class AvailabilityCartRequest {
    Set<Availability> reservationCartItems = new HashSet<>();

    public AvailabilityCartRequest() {
    }

    public AvailabilityCartRequest(Set<Availability> reservationCartItems) {
        this.reservationCartItems = reservationCartItems;
    }

    public Set<Availability> getReservationCartItems() {
        return reservationCartItems;
    }

    public void setReservationCartItems(Set<Availability> reservationCartItems) {
        this.reservationCartItems = reservationCartItems;
    }
}
