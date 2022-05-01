package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.payment.CheckoutPayment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ReservationArrangement {

    @Id
    private Long reservation_arrangement_id;
    private Integer partySize;
    private Integer numberOfRooms;
    @MappedCollection(idColumn = "RESERVATION_ARRANGEMENT_ID")
    private Set<Reservation> reservations = new HashSet<>();
    private String email;
    private Boolean confirmed;
    private BigDecimal price;

    public ReservationArrangement() {
    }

    public ReservationArrangement(Long reservation_arrangement_id,
                                  Integer partySize,
                                  Integer numberOfRooms,
                                  Set<Reservation> reservations,
                                  String email, Boolean confirmed,
                                  BigDecimal price) {
        this.reservation_arrangement_id = reservation_arrangement_id;
        this.partySize = partySize;
        this.numberOfRooms = numberOfRooms;
        this.reservations = reservations;
        this.email = email;
        this.confirmed = confirmed;
        this.price = price;
    }

    public Long getReservation_arrangement_id() {
        return reservation_arrangement_id;
    }

    public void setReservation_arrangement_id(Long reservation_arrangement_id) {
        this.reservation_arrangement_id = reservation_arrangement_id;
    }

    public int getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
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
