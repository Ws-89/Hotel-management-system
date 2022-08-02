package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.AvailabilityResponse;
import pl.siuda.hotel.requests.AvailabilityRequest;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.requests.ReservationRequest;
import pl.siuda.hotel.services.ReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/available-rooms")
    public AvailabilityResponse availableRooms(@RequestBody AvailabilityRequest request){
        return reservationService.getAvailableRooms(request);
    }

//    @PostMapping("/user/place-a-booking")
//    public void userPlaceABooking(@RequestBody ReservationRequest request) {
//        reservationService.userPlaceABooking(request);
//    }
//
    @PostMapping("/place-a-booking")
    public void placeABooking(@RequestBody ReservationRequest request) {
        reservationService.placeABooking(request);
    }

}
