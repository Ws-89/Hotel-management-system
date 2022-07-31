package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.models.Availability;
import pl.siuda.hotel.services.AvailabilityCartService;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/reservations/user/bookmarks")
public class AvailabilityCartController {

    private final AvailabilityCartService availabilityCartService;

    public AvailabilityCartController(AvailabilityCartService availabilityCartService) {
        this.availabilityCartService = availabilityCartService;
    }

    @PostMapping
    public void addToCart(@RequestBody Availability[] availability){
        this.availabilityCartService.addToCart(availability);
    }

    @GetMapping
    public Set<Availability> getAvailabilityCart(){

        return this.availabilityCartService.getAvailabilityCart();
    }

    @DeleteMapping("{id}")
    public void deleteCartItem(@PathVariable("id") long id){
        availabilityCartService.deleteCartItem(id);
    }
}
