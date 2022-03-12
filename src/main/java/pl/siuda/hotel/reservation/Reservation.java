package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Reservation {

    @Id
    private Long reservation_id;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private int partySize;
    @MappedCollection(keyColumn = "RESERVATION_ID", idColumn = "RESERVATION_ID")
    private Set<ReservationArrangement> rooms = new HashSet<>();
    private Long guest_id;

    public Set<ReservationArrangement> getReservations() {
        return rooms;
    }

    public void setReservations(Set<ReservationArrangement> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(long room_id){
        this.rooms.add(new ReservationArrangement(room_id));
    }

    public Reservation() {
    }

    public LocalDateTime getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDateTime from_date) {
        this.from_date = from_date;
    }

    public LocalDateTime getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDateTime to_date) {
        this.to_date = to_date;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }


    public Integer getPartySize() {
        return partySize;
    }

    public void setPartySize(Integer partySize) {
        this.partySize = partySize;
    }

    public Long getId() {
        return reservation_id;
    }

    public void setId(Long id) {
        this.reservation_id = id;
    }

    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }

    public Set<ReservationArrangement> getRooms() {
        return rooms;
    }

    public void setRooms(Set<ReservationArrangement> rooms) {
        this.rooms = rooms;
    }

    public Long getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(Long guest_id) {
        this.guest_id = guest_id;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
