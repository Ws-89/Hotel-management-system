package pl.siuda.hotel.room;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("management/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> listRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("{id}")
    public Room getRoomById(@PathVariable("id") Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/number{number}")
    public Room getRoomByNumber(@PathVariable("number") Integer number) {
        return roomService.getRoomByNumber(number);
    }

//    @PostMapping
//    public void createRoom(@RequestBody Room room){
//        roomService.addNewRom(room);
//    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

    @PutMapping("{id}")
    public void updateRoom(@PathVariable("id") Long id, @RequestBody Room room){
        roomService.updateRoom(id, room);
    }

    @GetMapping("/hotel/{id}")
    public List<Room> roomList(@PathVariable("id")Long id){ return roomService.findByHotelId(id); }

    @PostMapping("{id}")
    public void createRoomAtSpecifiedHotel(@PathVariable("id") Long id, @RequestBody Room room){
        roomService.createRoomAtSpecifiedHotel(id, room);
    }

}
