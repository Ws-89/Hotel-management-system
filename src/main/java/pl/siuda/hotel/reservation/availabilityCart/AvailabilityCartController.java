package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.reservation.Availability;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/availabilityCart")
public class AvailabilityCartController {

    private final AvailabilityCartService availabilityCartService;

    public AvailabilityCartController(AvailabilityCartService availabilityCartService) {
        this.availabilityCartService = availabilityCartService;
    }

    @PostMapping
    public void addToCart(@RequestBody Availability availability){
        this.availabilityCartService.addToCart(availability);
    }

    @GetMapping
    public AvailabilityCart getAvailabilityCart(){
        return this.availabilityCartService.getAvailabilityCart();
    }
}
