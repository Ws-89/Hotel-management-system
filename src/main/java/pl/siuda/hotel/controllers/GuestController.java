package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.siuda.hotel.services.GuestService;
import pl.siuda.hotel.models.Guest;

@RestController
@RequestMapping("/api/v1/user-management/users")
public class GuestController {

    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping("/{email}")
    public Guest getById(@PathVariable("email") String email){
        return guestService.findByEmail(email);
    }
}
