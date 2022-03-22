package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservation reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Set<Availability> getAvailability(@RequestBody AvailabilityRequest request){
        return reservationService.getAvailability(request);
    }

    @PostMapping("/makeAReservation")
    public List<Reservation> makeAReservation(@RequestBody List<ReservationRequest> request) {
        return reservationService.makeAReservation(request);
    }


}
