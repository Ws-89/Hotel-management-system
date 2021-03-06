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

    public ReservationArrangement() {
    }

    public ReservationArrangement(long reservation_arrangement_id,
                                  int partySize,
                                  int numberOfRooms,
                                  Set<Reservation> reservations,
                                  String email, boolean confirmed,
                                  BigDecimal price) {
        this.reservation_arrangement_id = reservation_arrangement_id;
        this.partySize = partySize;
        this.numberOfRooms = numberOfRooms;
        this.reservations = reservations;
        this.email = email;
        this.confirmed = confirmed;
        this.price = price;
    }

    public long getReservation_arrangement_id() {
        return reservation_arrangement_id;
    }

    public void setReservation_arrangement_id(long reservation_arrangement_id) {
        this.reservation_arrangement_id = reservation_arrangement_id;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
