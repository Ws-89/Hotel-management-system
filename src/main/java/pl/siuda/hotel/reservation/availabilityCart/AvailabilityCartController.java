package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.reservation.Availability;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/availabilityCart")
public class AvailabilityCartController {

    private final AvailabilityCartService availabilityCartService;

    public AvailabilityCartController(AvailabilityCartService availabilityCartService) {
        this.availabilityCartService = availabilityCartService;
    }

    @PostMapping
    public void addToCart(@RequestBody Availability[] availability){
        this.availabilityCartService.addToCart(availability);
    }

//    @PostMapping
//    public void addToCart(@RequestBody Availability availability){
//        this.availabilityCartService.addToCart(availability);
//    }

    @GetMapping
    public Set<Availability> getAvailabilityCart(){
        return this.availabilityCartService.getAvailabilityCart();
    }

    @DeleteMapping("{id}")
    public void deleteCartItem(@PathVariable("id") long id){
        availabilityCartService.deleteCartItem(id);
    }
}
