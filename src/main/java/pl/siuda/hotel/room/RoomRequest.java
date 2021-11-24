package pl.siuda.hotel.room;

import pl.siuda.hotel.enums.RoomType;

public class RoomRequest {
    private Integer number;
    private RoomType roomType;

    public void copyRequestToEntity(Room room){
        room.setNumber(number);
        room.setRoomType(roomType);
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
}
