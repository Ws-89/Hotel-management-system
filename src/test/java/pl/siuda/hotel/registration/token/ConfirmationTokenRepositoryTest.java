package pl.siuda.hotel.registration.token;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepository;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ConfirmationTokenRepositoryTest {

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;
    @Autowired
    GuestRepository guestRepository;

    @Test
    void updateConfirmedAt() {
        // given
        Address address = new Address("Kamienne schodki", "Warszawa", "Mazowieckie", "Polska", "00-275");
        Guest guest = new Guest("Anna", "Smith", "annasmith@gmail.com", "pass123", address, "123456789", ApplicationUserRole.GUEST);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        guest.addConfirmationTokens(confirmationToken);
        guestRepository.save(guest);
        // when
        ConfirmationToken unconfirmedToken = confirmationTokenRepository.findByToken(token).get();
        assertThat(unconfirmedToken.getConfirmedAt()).isNull();
        LocalDateTime confirmedAt = LocalDateTime.now();
        confirmationTokenRepository.updateConfirmedAt(token, confirmedAt);
        // then
        ConfirmationToken confirmedToken = confirmationTokenRepository.findByToken(token).get();
        assertThat(confirmedToken.getConfirmedAt()).isEqualTo(confirmedAt.truncatedTo(ChronoUnit.SECONDS));
    }

    @Test
    void getUserId() {
        // given
        Address address = new Address("Kamienne schodki", "Warszawa", "Mazowieckie", "Polska", "00-275");
        Guest guest = new Guest("Anna", "Smith", "annasmith@gmail.com", "pass123", address, "123456789", ApplicationUserRole.GUEST);
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15));
        guest.addConfirmationTokens(confirmationToken);
        Guest savedGuest = guestRepository.save(guest);
        // when
        Long guestId =  confirmationTokenRepository.getUserId(token);
        // then
        assertThat(guestId).isEqualTo(savedGuest.getGuest_id());

    }
}