package pl.siuda.hotel.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.security.ApplicationUserRole;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class GuestRepositoryTest {

    @Autowired
    GuestRepository guestRepository;

    @Test
    void injectedComponentAreNotNull(){
        assertThat(guestRepository).isNotNull();
    }

    @Test
    void userIsNotEnabledAppUser() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest guest = new Guest("Anna", "Smith", "annasmith@gmail.com", "pass123", address, "123456789", ApplicationUserRole.GUEST);
        guestRepository.save(guest);
        Guest Anna = guestRepository.findByEmail("annasmith@gmail.com");
        assertNotNull(Anna);
        assertThat(Anna.isEnabled()).isEqualTo(false);
    }

    @Test
    void enableAppUser() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest guest = new Guest("Anna", "Smith", "annasmith@gmail.com", "pass123", address, "123456789", ApplicationUserRole.GUEST);
        guestRepository.save(guest);
        Guest Anna = guestRepository.findByEmail("annasmith@gmail.com");
        assertNotNull(Anna);
        // when
        guestRepository.enableAppUser(Anna.getGuest_id());
        Guest enabledGuest = guestRepository.findByEmail("annasmith@gmail.com");
        assertThat(enabledGuest.isEnabled()).isTrue();
    }
}