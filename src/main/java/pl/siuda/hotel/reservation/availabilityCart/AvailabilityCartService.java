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

    public void save(Availability availability) {
        availabilityCartRepository.findByGuestId(getUserId()).ifPresentOrElse(
                element -> {
                    element.addCartItem(availability);
                    availabilityCartRepository.save(element);
                }, () -> {
                    Guest guest = guestService.getGuestById(getUserId());
                    AvailabilityCart availabilityCart = new AvailabilityCart();
                    availabilityCart.addCartItem(availability);
                    guest.setAvailabilityCart(availabilityCart);
                    guestService.save(guest);
                }
        );
    }

    public AvailabilityCart findByEmail() {
        return availabilityCartRepository.findByGuestId(getUserId()).orElseThrow(()-> new NotFoundException("No items found for current user"));
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
