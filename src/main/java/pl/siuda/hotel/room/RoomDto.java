package pl.siuda.hotel.room;

import pl.siuda.hotel.enums.RoomType;

import java.io.Serializable;

public class RoomDto implements Serializable {

    private Long room_id;
    private Integer number;

    public static RoomDto RoomToDto(Room room){
        RoomDto roomDto = new RoomDto();
        roomDto.room_id = room.getRoom_id();

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



}
