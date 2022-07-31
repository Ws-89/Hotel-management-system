package pl.siuda.hotel.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.repositories.AvailabilityCartRepository;
import pl.siuda.hotel.models.Availability;
import pl.siuda.hotel.repositories.AvailabilityRepository;

import java.util.Set;

@Service
public class AvailabilityCartService {

    private final AvailabilityCartRepository availabilityCartRepository;
    private final AvailabilityRepository availabilityRepository;
    private final GuestService guestService;

    public AvailabilityCartService(AvailabilityCartRepository availabilityCartRepository,
                                   AvailabilityRepository availabilityRepository,
                                   GuestService guestService) {
        this.availabilityCartRepository = availabilityCartRepository;
        this.availabilityRepository = availabilityRepository;
        this.guestService = guestService;
    }

    public void addToCart(Availability[] availability) {
        Guest guest = guestService.getGuestById(getUserId());
        guest.getAvailabilityCart().addCartItem(availability);
        guestService.save(guest);
    }

    public Set<Availability> getAvailabilityCart() {
        Guest guest = guestService.getGuestById(getUserId());
        return guest.getAvailabilityCart().getCartItems();
    }

    public void deleteCartItem(long id){
        Availability availability = availabilityRepository.findById(id).orElseThrow(() -> new NotFoundException("item with id " + id + " not found"));
        availabilityRepository.delete(availability);
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
        return guest.getGuestId();
    }
}
