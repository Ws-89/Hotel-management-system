package pl.siuda.hotel.room;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @GetMapping
    public List<Room> listRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/roomReservations")
    public List<Room> listRoomsWR() {
        return roomService.getAllRoomsWithReservation();
    }

    @GetMapping("/hotel/{id}")
    public List<Room> roomFromTheHotel(@PathVariable("id")Long id){
        return roomService.getAllRoomsInTheHotel(id);
    }

    @GetMapping("{id}")
    public Room getRoomById(@PathVariable("id") Long id) {
        return roomService.getRoomById(id);
    }

    @GetMapping("/number{number}")
    public Room getRoomByNumber(@PathVariable("number") Integer number) {
        return roomService.getRoomByNumber(number);
    }

    @PostMapping
    public void createRoom(@RequestBody Room room){
        roomService.addNewRom(room);
    }

    @DeleteMapping("{id}")
    public void deleteRoom(@PathVariable Long id){
        roomService.deleteRoom(id);
    }

    @PutMapping("{id}")
    public void updateRoom(@PathVariable("id") Long id, @RequestBody Room room){
        roomService.updateRoom(id, room);
    }


}
