package pl.siuda.hotel.guest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.registration.token.ConfirmationToken;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class GuestService implements UserDetailsService {

    Logger LOGGER = LoggerFactory.getLogger(GuestService.class);
    private final GuestRepo guestRepo;
    private final BCryptPasswordEncoder passwordEncoder;

    public GuestService(GuestRepo guestRepo, BCryptPasswordEncoder passwordEncoder) {
        this.guestRepo = guestRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return guestRepo.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found", email)));
    }

    public String signUpGuest(Guest guest){
        boolean userExists = guestRepo.findByEmail(guest.getEmail()).isPresent();
        if(userExists){
            throw new IllegalStateException("email already in use");
        }

        String encodedPassword = passwordEncoder.encode(guest.getPassword());

        guest.setPassword(encodedPassword);

        String token = UUID.randomUUID().toString();
        LOGGER.info(String.valueOf(LocalDateTime.now()));
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
