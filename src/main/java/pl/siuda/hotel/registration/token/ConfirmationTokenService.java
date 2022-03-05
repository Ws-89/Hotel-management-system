package pl.siuda.hotel.registration.token;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.guest.GuestRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final GuestRepository guestRepository;

    public ConfirmationTokenService(ConfirmationTokenRepository confirmationTokenRepository, GuestRepository guestRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
        this.guestRepository = guestRepository;
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
