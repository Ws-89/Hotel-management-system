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
    private Long room_id;
    private Integer number;

    private RoomType roomType;

    public Room() {
    }

    public void updateDetails(Room room){
        this.number = room.number;
        this.roomType = room.roomType;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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
                "id=" + room_id +
                ", number=" + number +
                ", roomType=" + roomType +
                '}';
    }
}
