package pl.siuda.hotel.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.siuda.hotel.models.Admin;
import pl.siuda.hotel.repositories.AdminRepository;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.repositories.GuestRepository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    CustomUserDetailsService customUserDetailsService;

    @Mock
    AdminRepository adminRepository;

    @Mock
    GuestRepository guestRepository;


    @Test
    void userNotExistsReturnsTrue() {
        // given
        String email = "kevinsmith@gmail.com";
        // when
        when(adminRepository.findByEmail(email)).thenReturn(null);
        when(guestRepository.findByEmail(email)).thenReturn(null);
        // then
        boolean expected = customUserDetailsService.userNotExists(email);
        assertThat(expected).isTrue();
    }

    @Test
    void userNotExistsReturnsAdminIsFalse() {
        // given
        String email = "kevinsmith@gmail.com";
        Admin admin = new Admin();
        // when
        when(adminRepository.findByEmail(email)).thenReturn(admin);
        when(guestRepository.findByEmail(email)).thenReturn(null);
        // then
        boolean expected = customUserDetailsService.userNotExists(email);
        assertThat(expected).isFalse();
    }

    @Test
    void userNotExists() {
        // given
        String email = "kevinsmith@gmail.com";
        Guest guest = new Guest();
        // when
        when(adminRepository.findByEmail(email)).thenReturn(null);
        when(guestRepository.findByEmail(email)).thenReturn(guest);
        // then
        boolean expected = customUserDetailsService.userNotExists(email);
        assertThat(expected).isFalse();
    }


    @Test
    void loadUserByUsernameUserNotFound() {
        // given
        String email = "kevinsmith@gmail.com";
        // when
        when(adminRepository.findByEmail(email)).thenReturn(null);
        when(guestRepository.findByEmail(email)).thenReturn(null);
        // then
        assertThrows(UsernameNotFoundException.class, ()-> customUserDetailsService.loadUserByUsername(email));
    }

    @Test
    void loadUserByUsernameAdminFound() {
        // given
        String email = "kevinsmith@gmail.com";
        Admin admin = new Admin();
        // when
        when(adminRepository.findByEmail(email)).thenReturn(admin);
        when(guestRepository.findByEmail(email)).thenReturn(null);
        // then
        UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(email);
        assertThat(customUserDetails).isNotNull();
    }

    @Test
    void loadUserByUsernameGuestFound() {
        // given
        String email = "kevinsmith@gmail.com";
        Guest guest = new Guest();
        // when
        when(adminRepository.findByEmail(email)).thenReturn(null);
        when(guestRepository.findByEmail(email)).thenReturn(guest);
        // then
        UserDetails customUserDetails = customUserDetailsService.loadUserByUsername(email);
        assertThat(customUserDetails).isNotNull();
    }
}