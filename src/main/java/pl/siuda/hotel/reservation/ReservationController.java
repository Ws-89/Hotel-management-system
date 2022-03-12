package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservation reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public Set<Offert> getAvailability(@RequestBody AvailabilityRequest request){
        return reservationService.getAvailability(request);
    }

    @PostMapping("/makeAReservation")
    public void makeAReservation(@RequestBody ReservationRequest request) {
        reservationService.makeAReservation(request);
    }


}
