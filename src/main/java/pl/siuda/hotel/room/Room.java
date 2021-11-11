package pl.siuda.hotel.room;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.reservation.ReservationArrangement;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Room {

    @Id
    private Long id;
    private Integer number;

    @MappedCollection(keyColumn = "id", idColumn = "id")
    private Set<ReservationArrangement> reservations = new HashSet<>();

    private RoomType roomType;

    public Room() {
    }

    public void updateDetails(Room room){
        this.number = room.number;
        this.roomType = room.roomType;
        this.reservations = room.reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<ReservationArrangement> getReservations() {
        return reservations;
    }

    public void setReservations(Set<ReservationArrangement> reservations) {
        this.reservations = reservations;
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(new ReservationArrangement(reservation.getId()));
    }

    public Set<Long> getReservationIds(){
        return this.reservations.stream()
                .map(ReservationArrangement::getReservation)
                .collect(Collectors.toSet());
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                ", reservations=" + reservations +
                ", roomType=" + roomType +
                '}';
    }
}
