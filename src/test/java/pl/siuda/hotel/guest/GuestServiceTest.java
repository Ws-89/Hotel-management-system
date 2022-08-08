package pl.siuda.hotel.guest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.siuda.hotel.dto.GuestDTO;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.repositories.GuestRepository;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.security.CustomUserDetailsService;
import pl.siuda.hotel.services.GuestService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GuestServiceTest {

    @InjectMocks
    GuestService guestService;

    @Mock
    GuestRepository guestRepository;

    @Mock(name = "bCryptPasswordEncoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    CustomUserDetailsService customUserDetailsService;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(guestService).isNotNull();
        assertThat(guestRepository).isNotNull();
        assertThat(bCryptPasswordEncoder).isNotNull();
        assertThat(customUserDetailsService).isNotNull();
    }

    @Test
    void guestList() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Page<Guest> guestList = new PageImpl<>(Arrays.asList(
                Guest.builder().firstName("John").lastName("Doe").email("johndoe@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build(),
                Guest.builder().firstName("Kevin").lastName("Smith").email("kevinsmith@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build(),
                Guest.builder().firstName("Anna").lastName("Adams").email("annaadams@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build(),
                Guest.builder().firstName("Katherine").lastName("Brown").email("katherinebrown@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build()));
        // when
        when(guestRepository.findAll(PageRequest.of(0, 10))).thenReturn(guestList);
        // then
        Page<GuestDTO> guestListToAssert = guestService.guestList(0 , 10);
        assertThat(guestListToAssert.getTotalElements()).isEqualTo(4);
    }

    @Test
    void guestListIsEmpty() {
        // when
        when(guestRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));
        // then
        Page<GuestDTO> guestListToAssert = guestService.guestList(0, 10);
        assertThat(guestListToAssert.getTotalElements()).isEqualTo(0);
    }


    @Test
    void getGuestById() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest John = Guest.builder().firstName("John").lastName("Doe").email("johndoe@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build();
        // when
        when(guestRepository.findById(1L)).thenReturn(java.util.Optional.of(John));
        GuestDTO guest = guestService.getGuestById(1L);
        // then
        assertThat(guest.getGuestId()).isEqualTo(John.getGuestId());
        assertThat(guest.getFirstName()).isEqualTo(John.getFirstName());
        assertThat(guest.getLastName()).isEqualTo(John.getLastName());
        assertThat(guest.getEmail()).isEqualTo(John.getEmail());
        assertThat(guest.getAddress()).isEqualTo(John.getGuestAddress());
        assertThat(guest.getPhoneNumber()).isEqualTo(John.getPhoneNumber());
    }

    @Test
    void getGuestByIdNotFound(){
        when(guestRepository.findById(1L)).thenThrow(new NotFoundException("Guest with id 1 not found"));

        assertThrows(NotFoundException.class, () -> guestService.getGuestById(1L));
    }

    @Test
    void signUpGuest() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest John = Guest.builder().firstName("John").lastName("Doe").email("johndoe@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build();
        // when
        when(customUserDetailsService.userNotExists("johndoe@gmail.com")).thenReturn(true);
        when(bCryptPasswordEncoder.encode("pass123")).thenReturn("1");
        guestService.signUpGuest(John);
        // then
        ArgumentCaptor<Guest> guestArgumentCaptor = ArgumentCaptor.forClass(Guest.class);
        verify(guestRepository).save(guestArgumentCaptor.capture());

        Guest argumentCaptorValue = guestArgumentCaptor.getValue();
        assertThat(argumentCaptorValue.getFirstName()).isEqualTo(John.getFirstName());
        assertThat(argumentCaptorValue.getLastName()).isEqualTo(John.getLastName());
        assertThat(argumentCaptorValue.getEmail()).isEqualTo(John.getEmail());
        assertThat(argumentCaptorValue.getPassword()).isEqualTo("1");
        assertThat(argumentCaptorValue.getGuestAddress()).isEqualTo(John.getGuestAddress());
        assertThat(argumentCaptorValue.getPhoneNumber()).isEqualTo(John.getPhoneNumber());
    }

    @Test
    void signUpGuestThrowsExceptionEmailTaken() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest John = Guest.builder().firstName("John").lastName("Doe").email("johndoe@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build();
        // when
        when(customUserDetailsService.userNotExists("johndoe@gmail.com")).thenReturn(false);
        // then
        assertThrows(NotFoundException.class, () -> guestService.signUpGuest(John));
    }

    @Test
    void findByEmailReturnsGuest() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Guest John = Guest.builder().firstName("John").lastName("Doe").email("johndoe@gmail.com").password("pass123").guestAddress(address).phoneNumber("123456789").applicationUserRole(ApplicationUserRole.GUEST).build();
        // when
        when(guestRepository.findByEmail("johndoe@gmail.com")).thenReturn(John);
        Guest guest = guestService.findByEmail("johndoe@gmail.com");
        // then
        assertThat(guest.getFirstName()).isEqualTo(John.getFirstName());
        assertThat(guest.getLastName()).isEqualTo(John.getLastName());
        assertThat(guest.getEmail()).isEqualTo(John.getEmail());
        assertThat(guest.getPassword()).isEqualTo(John.getPassword());
        assertThat(guest.getGuestAddress()).isEqualTo(John.getGuestAddress());
        assertThat(guest.getPhoneNumber()).isEqualTo(John.getPhoneNumber());
    }

    @Test
    void findByEmailThrowsExceptionUserNotFound() {
        // when
        when(guestRepository.findByEmail("johndoe@gmail.com")).thenThrow(new NotFoundException("username not found"));
        // then
        assertThrows(NotFoundException.class, ()-> guestService.findByEmail("johndoe@gmail.com"));
    }

  
}