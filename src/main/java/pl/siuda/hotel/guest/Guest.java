package pl.siuda.hotel.guest;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.reservation.Reservation;

import java.util.HashSet;
import java.util.Set;

public class Guest {

    @Id
    @Column("GUEST_ID")
    private Long guest_id;
    @MappedCollection(keyColumn = "GUEST_ID", idColumn = "GUEST_ID")
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public Long getGuest_id() {
        return guest_id;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
