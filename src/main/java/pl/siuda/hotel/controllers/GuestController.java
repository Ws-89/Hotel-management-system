package pl.siuda.hotel.controllers;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.dto.GuestDTO;
import pl.siuda.hotel.models.HttpResponse;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.services.GuestService;
import pl.siuda.hotel.models.Guest;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user-management/users")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getAllGuests(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", guestService.guestList(page.orElse(0), size.orElse(10))))
                        .message("Guests list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/{email}")
    public ResponseEntity<HttpResponse> getById(@PathVariable("email") String email){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", guestService.findByEmail(email)))
                        .message("Guests list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get-by-id/{guestId}")
    public ResponseEntity<HttpResponse> getGuestById(@PathVariable("guestId") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", guestService.getGuestById(id)))
                        .message("Guests list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/get-principal")
    public ResponseEntity<HttpResponse> getPrincipal(){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", guestService.getPrincipal()))
                        .message("Current guest profile retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpResponse> updateGuest(@PathVariable("id") Long id, @RequestBody Guest guest){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", guestService.update(id, guest)))
                        .message("Guests updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> updateGuest(@PathVariable("id") Long id){
        guestService.deleteGuest(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("Guests deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

//    @GetMapping("/by-hotel-id/{hotelId}")
//    public ResponseEntity<HttpResponse> findAllByRoom_Hotel_HotelId(@PathVariable("hotelId") Long hotelId,
//                                                                    @RequestParam Optional<ReservationStatus> status,
//                                                                    @RequestParam Optional<Integer> page,
//                                                                    @RequestParam Optional<Integer> size){
//        return ResponseEntity.ok().body(
//                HttpResponse.builder().timeStamp(now().toString())
//                        .data(Map.of("page", guestService.guestListByHotel(hotelId, status.orElse(ReservationStatus.Initialized),  page.orElse(0), size.orElse(10))))
//                        .message(String.format("Guests of hotel with id %d retrieved", hotelId))
//                        .status(OK)
//                        .statusCode(OK.value())
//                        .build()
//        );
//    }
}
