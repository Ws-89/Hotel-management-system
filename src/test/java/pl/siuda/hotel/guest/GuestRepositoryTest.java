package pl.siuda.hotel.guest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@AutoConfigureTestDatabase
@ActiveProfiles("test")
class GuestRepositoryTest {

    @Autowired
    GuestRepository guestRepository;

    @Test
    void injectedComponentAreNotNull(){
        assertThat(guestRepository).isNotNull();
    }

    @Test
    void enableAppUser() {
        Guest guest = new Guest();

    }
}