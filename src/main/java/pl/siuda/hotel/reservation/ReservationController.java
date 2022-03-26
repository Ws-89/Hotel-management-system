package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public List<Availability> getAvailability(@RequestBody AvailabilityRequest request){
        return reservationService.getAvailability(request);
    }

    @PostMapping("/makeAReservation")
    public void makeAReservation(@RequestBody ReservationRequest request) {
        reservationService.makeAReservation(request);
    }
}
