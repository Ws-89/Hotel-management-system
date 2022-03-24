package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.siuda.hotel.reservation.Availability;

import java.util.List;


@RestController
@RequestMapping("/availabilityCart")
public class AvailabilityCartController {

    private final AvailabilityCartService availabilityCartService;

    public AvailabilityCartController(AvailabilityCartService availabilityCartService) {
        this.availabilityCartService = availabilityCartService;
    }

    @PostMapping
    public void addToCart(Availability availability){
        this.availabilityCartService.save(availability);
    }

    @GetMapping
    public AvailabilityCart getReservationItems(){
        return this.availabilityCartService.findByEmail();
    }
}
