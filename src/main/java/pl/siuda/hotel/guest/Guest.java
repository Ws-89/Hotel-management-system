package pl.siuda.hotel.guest;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.reservation.Reservation;

import java.util.HashSet;
import java.util.Set;

public class Guest {

    @Id
    @Column("ID")
    private Long guestId;
    @MappedCollection(keyColumn = "guestid", idColumn = "guestid")
    private Set<Reservation> reservations = new HashSet<>();

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public Long getGuestId() {
        return guestId;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
