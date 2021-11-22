package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/roomsByCity/{city}")
    public Set<Availability> getRoomsByCity(@PathVariable("city") String city){
        return reservationService.getRoomsByCity(city);
    }

    @PostMapping
    public Set<Availability> getAvailability(@RequestBody AvailabilityRequest request){
        return reservationService.getAvailability(request);
    }


}
