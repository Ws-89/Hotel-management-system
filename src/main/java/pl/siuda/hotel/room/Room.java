package pl.siuda.hotel.room;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.reservation.Reservation;

import java.util.HashSet;
import java.util.Set;

public class Room {

    @Id
    private Long id;
    private Integer number;

    @MappedCollection(idColumn = "room_id")
    private Set<Reservation> reservations = new HashSet<>();

    private RoomType roomType;

    public Room() {
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

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
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
