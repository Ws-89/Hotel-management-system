package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ReservationArrangement {

    @Id
    long reservation_arrangement_id;
    int partySize;
    int numberOfRooms;
    @MappedCollection(idColumn = "RESERVATION_ARRANGEMENT_ID")
    Set<Reservation> reservations = new HashSet<>();
    String email;
    boolean confirmed;
    BigDecimal price;

}
