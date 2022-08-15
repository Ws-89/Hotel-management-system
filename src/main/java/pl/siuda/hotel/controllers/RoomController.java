package pl.siuda.hotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.HttpResponse;
import pl.siuda.hotel.models.Room;
import pl.siuda.hotel.requests.RoomRequest;
import pl.siuda.hotel.services.RoomService;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hotel-management/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("{id}")
    public ResponseEntity<HttpResponse> getById(@PathVariable("id") UUID id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", roomService.getRoomById(id)))
                        .message("room retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/by-hotel/{id}")
    public ResponseEntity<HttpResponse> findRoomsByHotelId(@PathVariable("id")UUID id, @RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", roomService.findRoomsByHotelId(id, page.orElse(0), size.orElse(10))))
                        .message("room list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("/by-hotel/{id}")
    public ResponseEntity<HttpResponse> createRoom(@PathVariable("id")UUID id, @RequestBody RoomRequest request) {
        this.roomService.createRoom(id, request);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("room created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpResponse> deleteGroup(@PathVariable("id")UUID id){
        this.roomService.deleteRoom(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("room deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpResponse> updateRoom(@PathVariable("id")UUID id, @RequestBody Room request){
        this.roomService.updateRoom(id, request);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("room updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/switch-room-state/{id}")
    public ResponseEntity<HttpResponse> switchRoomActivation(@PathVariable("id")UUID id, @RequestParam Boolean state){
        this.roomService.switchRoomActivation(id, state);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("switched room activation")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
