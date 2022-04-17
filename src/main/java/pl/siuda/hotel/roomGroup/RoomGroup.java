package pl.siuda.hotel.roomGroup;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.enums.RoomType;
import pl.siuda.hotel.room.Room;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RoomGroup {

    @Id
    private Long room_group_id;
    @MappedCollection(idColumn = "ROOM_GROUP_ID")
    private Set<Room> rooms = new HashSet<>();
    private String description;
    private RoomType roomType;
    private int quantity_of_rooms;

    public RoomGroup() {
    }

    public int getQuantity_of_rooms() {
        return quantity_of_rooms;
    }

    public void setQuantity_of_rooms(int quantity_of_rooms) {
        this.quantity_of_rooms = quantity_of_rooms;
    }

    public long getRoom_group_id() {
        return room_group_id;
    }

    public void setRoom_group_id(long room_group_id) {
        this.room_group_id = room_group_id;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room){
        this.rooms.add(room);
    }

    public void removeFromGroup(){
        Iterator<Room> i = rooms.iterator();
        Room r;
        if(i.hasNext()){
            r = i.next();
            rooms.remove(r);
            quantity_of_rooms = quantity_of_rooms -1;
        }
    }

    public void setRoom_group_id(Long room_group_id) {
        this.room_group_id = room_group_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }
}
