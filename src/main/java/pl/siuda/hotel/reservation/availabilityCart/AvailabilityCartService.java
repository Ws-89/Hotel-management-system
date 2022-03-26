package pl.siuda.hotel.reservation.availabilityCart;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestService;
import pl.siuda.hotel.reservation.Availability;

@Service
public class AvailabilityCartService {

    private final AvailabilityCartRepository availabilityCartRepository;
    private final GuestService guestService;

    public AvailabilityCartService(AvailabilityCartRepository availabilityCartRepository, GuestService guestService) {
        this.availabilityCartRepository = availabilityCartRepository;
        this.guestService = guestService;
    }

    public void addToCart(Availability availability) {
        Guest guest = guestService.getGuestById(getUserId());
//        if(guest.getAvailabilityCart() == null){
//            AvailabilityCart availabilityCart = new AvailabilityCart();
//            availabilityCart.addCartItem(availability);
//            guest.setAvailabilityCart(availabilityCart);
//            guestService.save(guest);
//        }else
        guest.getAvailabilityCart().addCartItem(availability);
        guestService.save(guest);
    }

    public AvailabilityCart getAvailabilityCart() {
        Guest guest = guestService.getGuestById(getUserId());
        return guest.getAvailabilityCart();
    }

    private Long getUserId() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Guest guest;
        if(authentication instanceof UserDetails){
            String email = ((UserDetails) authentication).getUsername();
            guest = guestService.findByEmail(email);
        }else {
            throw new IllegalArgumentException();
        }
        return guest.getGuest_id();
    }
}
