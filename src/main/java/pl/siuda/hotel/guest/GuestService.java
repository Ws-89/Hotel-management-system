package pl.siuda.hotel.guest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.EmailValidator;
import pl.siuda.hotel.reservation.availabilityCart.AvailabilityCart;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class GuestService {

    private final GuestRepository guestRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailValidator emailValidator;
    private final CustomUserDetailsService customUserDetailsService;

    public GuestService(GuestRepository guestRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailValidator emailValidator, CustomUserDetailsService customUserDetailsService) {
        this.guestRepository = guestRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailValidator = emailValidator;
        this.customUserDetailsService = customUserDetailsService;
    }

    public List<Guest> guestList(){
        return StreamSupport.stream(guestRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Guest getGuestById(Long id){
        return guestRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Guest with id %s not found")));
    }

    public Guest signUpGuest(Guest guest){
        boolean userNotExists = customUserDetailsService.userNotExists(guest.getEmail());
        if(!userNotExists){
            throw new NotFoundException("email already in use");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(guest.getPassword());
        guest.setPassword(encodedPassword);

        AvailabilityCart availabilityCart = new AvailabilityCart();
        guest.setAvailabilityCart(availabilityCart);

        return guestRepository.save(guest);
    }

    public Guest findByEmail(String email){
        Guest guest = guestRepository.findByEmail(email);
        if(guest == null){
            throw new NotFoundException("username not found");
        }
        return guest;
    }

    public void save(Guest guest){
        this.guestRepository.save(guest);
    }
}
