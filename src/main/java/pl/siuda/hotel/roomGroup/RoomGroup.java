package pl.siuda.hotel.roomGroup;

import org.springframework.data.annotation.Id;

import pl.siuda.hotel.models.enums.RoomType;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RoomGroup {

    @Id
    private Long roomGroupId;

    private Set<Room> rooms = new HashSet<>();
    private String description;
    private RoomType roomType;
    private int quantityOfRooms;

    public RoomGroup() {
    }

    public int getQuantityOfRooms() {
        return quantityOfRooms;
    }

    public void setQuantityOfRooms(int quantityOfRooms) {
        this.quantityOfRooms = quantityOfRooms;
    }

    public long getRoomGroupId() {
        return roomGroupId;
    }

    public void setRoomGroupId(long roomGroupId) {
        this.roomGroupId = roomGroupId;
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
            quantityOfRooms = quantityOfRooms -1;
        }
    }

    public void setRoom_group_id(Long room_group_id) {
        this.roomGroupId = room_group_id;
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
