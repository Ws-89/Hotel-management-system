package pl.siuda.hotel.roomGroup;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/management/room-groups")
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

    @PostMapping("{id}")
    public void createRoomGroup(@PathVariable("id")Long id, @RequestBody RoomGroup roomGroup) {
        this.roomGroupService.createRoomGroup(id, roomGroup);
    }

    @DeleteMapping("{id}")
    public void deleteFromGroup(@PathVariable("id")Long id){
        this.roomGroupService.removeFromGroup(id);
    }

    @GetMapping("/existing-groups/{id}")
    public RoomGroup addToRoomGroup(@PathVariable("id")Long id){
        return roomGroupService.addToRoomGroup(id);
    }

    @DeleteMapping("/delete-group/{id}")
    public void deleteGroup(@PathVariable("id")Long id){
        this.roomGroupService.deleteGroup(id);
    }

    @PutMapping
    public void updateRoomGroup(@RequestBody RoomGroup roomGroup){
        this.roomGroupService.updateRoomGroup(roomGroup);
    }
}
