package pl.siuda.hotel.guest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.token.ConfirmationToken;
import pl.siuda.hotel.security.CustomUserDetails;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.time.LocalDateTime;
import java.util.UUID;

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

        // TODO: Send email

        return token;
    }

    public Long enableAppUser(Long id) {
        return guestRepo.enableAppUser(id);
    }
}
