package pl.siuda.hotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.HttpResponse;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.ReservationStatus;
import pl.siuda.hotel.services.ReservationService;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/reservations-management")
public class ReservationManagementController {

    private final ReservationService reservationService;

    public ReservationManagementController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpResponse> deleteReservation(@PathVariable("id") Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message(String.format("Reservation with id %d deleted", id))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HttpResponse> updateReservation(@PathVariable("id") Long id, @RequestBody Reservation reservation){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", reservationService.updateReservation(id, reservation)))
                        .message("Reservation updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/by-guest-id/{guestId}")
    public ResponseEntity<HttpResponse> findAllByGuestId(@PathVariable("guestId") Long guestId,
                                                         @RequestParam Optional<ReservationStatus> status,
                                                         @RequestParam Optional<Integer> page,
                                                         @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", reservationService.findAllByGuest_GuestId(status.orElse(ReservationStatus.Initialized), guestId,  page.orElse(0), size.orElse(10))))
                        .message(String.format("Reservations of guest with id %d retrieved", guestId))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/by-hotel-id/{hotelId}")
    public ResponseEntity<HttpResponse> findAllByRoom_Hotel_HotelId(@PathVariable("hotelId") Long hotelId,
                                                                    @RequestParam Optional<ReservationStatus> status,
                                                                    @RequestParam Optional<Integer> page,
                                                                    @RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", reservationService.findAllByRoom_Hotel_HotelId(status.orElse(ReservationStatus.Active), hotelId, page.orElse(0), size.orElse(10))))
                        .message(String.format("Reservations of hotel with id %d retrieved", hotelId))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


    @GetMapping("/by-room-id/{roomId}")
    public ResponseEntity<HttpResponse> findAllByRoom_RoomId(
                                                             @RequestParam Optional<ReservationStatus> status,
                                                             @RequestParam Optional<Integer> page,
                                                             @RequestParam Optional<Integer> size, @PathVariable("roomId") Long roomId){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", reservationService.findAllByReservationStatusAndRoom_RoomId(status.orElse(ReservationStatus.Initialized), roomId, page.orElse(0), size.orElse(10))))
                        .message(String.format("Reservations of room with id %d retrieved", roomId))
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/find-by-id/{id}")
    public ResponseEntity<HttpResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", reservationService.findById(id)))
                        .message("Reservation retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }
}
