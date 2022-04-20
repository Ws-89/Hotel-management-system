package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/available-rooms")
    public List<Availability> availableRooms(@RequestBody AvailabilityRequest request){
        return reservationService.availableRooms(request);
    }

    @PostMapping("/user/place-a-booking")
    public void userPlaceABooking(@RequestBody ReservationRequest request) {
        reservationService.userPlaceABooking(request);
    }

    @PostMapping("/place-a-booking")
    public void placeABooking(@RequestBody ReservationRequest request) {
        reservationService.placeABooking(request);
    }

}
