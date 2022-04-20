package pl.siuda.hotel.roomGroup;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hotel-management/room-groups")
public class RoomGroupController {

    private final RoomGroupService roomGroupService;

    public RoomGroupController(RoomGroupService roomGroupService) {
        this.roomGroupService = roomGroupService;
    }

    @GetMapping("{id}")
    public RoomGroup getById(@PathVariable("id")Long id){
        return roomGroupService.getRoomGroupById(id);
    }

    @GetMapping("/by-hotel/{id}")
    public List<RoomGroup> findRoomGroupsByHotelId(@PathVariable("id")Long id){
        return roomGroupService.findRoomGroupsByHotelId(id);
    }

    @PostMapping("/by-hotel/{id}")
    public void createRoomGroup(@PathVariable("id")Long id, @RequestBody RoomGroup roomGroup) {
        this.roomGroupService.createRoomGroup(id, roomGroup);
    }

    @DeleteMapping("/{id}/remove-room")
    public void deleteFromGroup(@PathVariable("id")Long id){
        this.roomGroupService.removeFromGroup(id);
    }

    @GetMapping("/{id}/add-room")
    public RoomGroup addToRoomGroup(@PathVariable("id")Long id){
        return roomGroupService.addToRoomGroup(id);
    }

    @DeleteMapping("{id}")
    public void deleteGroup(@PathVariable("id")Long id){
        this.roomGroupService.deleteGroup(id);
    }

    @PutMapping("{id}")
    public void updateRoomGroup(@PathVariable("id")Long id, @RequestBody RoomGroup roomGroup){
        this.roomGroupService.updateRoomGroup(id, roomGroup);
    }
}
