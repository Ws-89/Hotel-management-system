package pl.siuda.hotel.reservation;

import org.springframework.data.relational.core.mapping.MappedCollection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReservationRequest {

    int partySize;
    int numberOfRooms;
    Set<Reservation> reservations = new HashSet<>();
    String email;
    boolean confirmed;
    BigDecimal price;

    public ReservationRequest() {
    }

    public ReservationRequest(int partySize, int numberOfRooms, Set<Reservation> reservations, String email, boolean confirmed, BigDecimal price) {
        this.partySize = partySize;
        this.numberOfRooms = numberOfRooms;
        this.reservations = reservations;
        this.email = email;
        this.confirmed = confirmed;
        this.price = price;
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
