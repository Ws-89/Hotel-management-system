package pl.siuda.hotel.guest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.token.ConfirmationToken;
import pl.siuda.hotel.security.CustomUserDetails;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class GuestService {

    private final GuestRepo guestRepo;
    private final BCryptPasswordEncoder passwordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public GuestService(GuestRepo guestRepo, BCryptPasswordEncoder passwordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.guestRepo = guestRepo;
        this.passwordEncoder = passwordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public List<Guest> guestList(){
        return StreamSupport.stream(guestRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Guest getGuestById(Long id){
        return guestRepo.findById(id).orElseThrow(()-> new NotFoundException(String.format("Guest with id %s not found")));
    }

    public String signUpGuest(Guest guest){
        boolean userNotExists = customUserDetailsService.userNotExists(guest.getEmail());
        if(!userNotExists){
            throw new NotFoundException("email already in use");
        }

        String encodedPassword = passwordEncoder.encode(guest.getPassword());

        guest.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15)
        );

        guest.addConfirmationTokens(confirmationToken);
        guestRepo.save(guest);

        return token;
    }

    public Guest findByEmail(String email){
        Guest guest = guestRepo.findByEmail(email);
        if(guest == null){
            throw new NotFoundException("username not found");
        }
        return guest;
    }

    public Long enableAppUser(Long id) {
        return guestRepo.enableAppUser(id);
    }
}
