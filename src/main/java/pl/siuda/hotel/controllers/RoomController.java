package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.services.RoomService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hotel-management/room-groups")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("{id}")
    public Room getById(@PathVariable("id")Long id){
        return roomService.getRoomById(id);
    }

    @GetMapping("/by-hotel/{id}")
    public List<Room> findRoomsByHotelId(@PathVariable("id")Long id){
        return roomService.findRoomsByHotelId(id);
    }

    @PostMapping("/by-hotel/{id}")
    public void createRoom(@PathVariable("id")Long id, @RequestBody Room room) {
        this.roomService.createRoom(id, room);
    }


    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable("id")Long id){
        this.roomService.deleteGroup(id);
    }

    @PutMapping("{id}")
    public void updateRoomGroup(@PathVariable("id")Long id, @RequestBody Room roomGroup){
        this.roomService.updateRoom(id, roomGroup);
    }
}
