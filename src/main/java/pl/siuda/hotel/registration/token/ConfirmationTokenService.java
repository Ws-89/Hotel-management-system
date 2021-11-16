package pl.siuda.hotel.registration.token;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.guest.GuestRepo;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final GuestRepo guestRepo;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, GuestRepo guestRepo) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.guestRepo = guestRepo;
    }

    public void saveConfirmationToken(ConfirmationToken token) {
        confirmationTokenRepository.save(token);
    }

    public Optional<ConfirmationToken> getToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }

    public Long getUserId(String token){
        return confirmationTokenRepository.getUserId(token);
    }
}
