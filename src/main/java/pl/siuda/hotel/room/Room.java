package pl.siuda.hotel.room;

import org.springframework.data.annotation.Id;
import pl.siuda.hotel.enums.RoomType;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Room implements Serializable {

    @Id
    private Long room_id;

    public Room() {
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }




    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + room_id +
                '}';
    }
}
