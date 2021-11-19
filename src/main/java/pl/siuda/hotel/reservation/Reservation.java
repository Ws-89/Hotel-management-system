package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.room.Room;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Reservation {

    @Id
    private Long reservation_id;
    private LocalDateTime from_data;
    private LocalDateTime to_data;
    private Integer partySize;
    @MappedCollection(keyColumn = "RESERVATION_ID", idColumn = "RESERVATION_ID")
    private Set<ReservationArrangement> rooms = new HashSet<>();

    public Set<ReservationArrangement> getReservations() {
        return rooms;
    }

    public void setReservations(Set<ReservationArrangement> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room){
        this.rooms.add(new ReservationArrangement(room.getRoom_id()));
    }

    public Reservation() {
    }

    public LocalDateTime getFrom_data() {
        return from_data;
    }

    public void setFrom_data(LocalDateTime from_data) {
        this.from_data = from_data;
    }

    public LocalDateTime getTo_data() {
        return to_data;
    }

    public void setTo_data(LocalDateTime to_data) {
        this.to_data = to_data;
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

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
