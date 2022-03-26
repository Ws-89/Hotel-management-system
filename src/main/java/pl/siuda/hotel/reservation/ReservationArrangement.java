package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.util.HashSet;
import java.util.Set;

public class ReservationArrangement {

    @Id
    long reservation_arrangement_id;
    int partySize;
    int numberOfRooms;
    @MappedCollection(idColumn = "RESERVATION_ARRANGEMENT_ID")
    Set<Availability> reservations = new HashSet<>();
    String email;

}
