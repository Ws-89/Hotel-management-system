package pl.siuda.hotel.room;

import pl.siuda.hotel.enums.RoomType;

public class RoomDto {

    private Long room_id;
    private Integer number;
    private RoomType roomType;

    public static RoomDto RoomToDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.room_id = room.getRoom_id();
        roomDto.number = room.getNumber();
        roomDto.roomType = room.getRoomType();
        return roomDto;
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
}
