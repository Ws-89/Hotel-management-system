package pl.siuda.hotel.roomGroup;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class Room implements Serializable {

    @Id
    private Long roomId;

    public Room() {
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }




    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + roomId +
                '}';
    }
}
