package pl.siuda.hotel.room;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/management/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<RoomDto> listRooms(){
        return roomService.getAllRooms().stream().map(RoomDto::RoomToDto).collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public RoomDto getRoomById(@PathVariable("id") Long id) {
        return RoomDto.RoomToDto(roomService.getRoomById(id));
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

//    @PutMapping("{id}")
//    public RoomDto updateRoom(@PathVariable("id") Long id, @RequestBody RoomRequest roomRequest){
//        return RoomDto.RoomToDto(roomService.updateRoom(id, roomRequest));
//    }

    @GetMapping("/hotel/{id}")
    public List<RoomDto> roomList(@PathVariable("id")Long id){
        return roomService.findByHotelId(id).stream().map(RoomDto::RoomToDto).collect(Collectors.toList()); }

//    @PostMapping("{id}")
//    public void createRoomAtSpecifiedHotel(@PathVariable("id") Long id, @RequestBody RoomRequest roomRequest){
//        roomService.createRoomAtSpecifiedHotel(id, roomRequest);
//    }

}
