package pl.siuda.hotel.reservation;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
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
