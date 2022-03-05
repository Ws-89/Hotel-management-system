package pl.siuda.hotel.room;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.amazonS3bucket.Image;
import pl.siuda.hotel.enums.RoomType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Room implements Serializable {

    @Id
    private Long room_id;
    private Integer number;
    private String description;
    private RoomType roomType;

    public Room() {
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


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                ", number=" + number +
                ", description='" + description + '\'' +
                ", roomType=" + roomType +
                '}';
    }
}
