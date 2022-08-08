package pl.siuda.hotel.hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pl.siuda.hotel.dto.HotelWithoutRoomsDTO;
import pl.siuda.hotel.requests.HotelRequest;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.services.HotelService;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {

    @InjectMocks
    HotelService hotelService;

    @Mock
    HotelRepository hotelRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(hotelService).isNotNull();


        assertThat(hotelRepository).isNotNull();
    }

    @Test
    void getAllHotels() {
        // given
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Page<Hotel> hotelList = new PageImpl<>(Arrays.asList(
                Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build(),
                Hotel.builder().hotelId(2L).name("Pokoje na wsi").address(address).contact(contact).grade(Grade.TWOSTARS).build(),
                Hotel.builder().hotelId(3L).name("Pokoje nad morzem").address(address).contact(contact).grade(Grade.THREESTARS).build(),
                Hotel.builder().hotelId(4L).name("Hotel").address(address).contact(contact).grade(Grade.FOURSTARS).build(),
                Hotel.builder().hotelId(5L).name("Hotel w górach").address(address).contact(contact).grade(Grade.FIVESTARS).build()));
        // when
        when(hotelRepository.findAll(PageRequest.of(0, 10))).thenReturn(hotelList);
        Page<HotelWithoutRoomsDTO> hotels = hotelService.getHotelsByCity(0 ,10);
        // then
        assertThat(hotels.getTotalElements()).isEqualTo(5);
    }

    @Test
    void getAllHotelsReturnsEmptyList() {
        // when
        when(hotelRepository.findAll(PageRequest.of(0, 10))).thenReturn(new PageImpl<>(Collections.emptyList()));
        Page<HotelWithoutRoomsDTO> hotels = hotelService.getHotelsByCity(0 ,10);
        // then
        assertThat(hotels.getTotalElements()).isEqualTo(0);
    }

    @Test
    void getHotelById() {
        // given
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();
        // when
        when(hotelRepository.findById(1L)).thenReturn(java.util.Optional.of(pokojeWMiescie));
        HotelWithoutRoomsDTO hotel = hotelService.getHotelById(1L);
        // then
        assertThat(hotel.getHotelId()).isEqualTo(pokojeWMiescie.getHotelId());
        assertThat(hotel.getName()).isEqualTo(pokojeWMiescie.getName());
        assertThat(hotel.getAddress()).isEqualTo(pokojeWMiescie.getAddress());
        assertThat(hotel.getContact()).isEqualTo(pokojeWMiescie.getContact());
        assertThat(hotel.getGrade()).isEqualTo(pokojeWMiescie.getGrade());
    }

    @Test
    void getHotelByIdNotFound(){
        // when
        when(hotelRepository.findById(1L)).thenThrow(NotFoundException.class);
        // then
        assertThrows(NotFoundException.class, ()-> hotelService.getHotelById(1L));
    }

    @ParameterizedTest
    @EnumSource(Grade.class)
    void createHotel(Grade grade) {
        // given
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        HotelRequest hotelRequest = HotelRequest.builder().name("Pokoje w miescie").grade(Grade.FIVESTARS).contact(contact).address(address).build();
        // when
        when(hotelRepository.findByName("Pokoje w miescie")).thenReturn(Optional.empty());
        hotelService.createHotel(hotelRequest);
        // then
        ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(hotelArgumentCaptor.capture());

        Hotel hotelArgumentCaptorValue = hotelArgumentCaptor.getValue();

        assertThat(hotelArgumentCaptorValue.getName()).isEqualTo(hotelRequest.getName());
        assertThat(hotelArgumentCaptorValue.getAddress()).isEqualTo(hotelRequest.getAddress());
        assertThat(hotelArgumentCaptorValue.getContact()).isEqualTo(hotelRequest.getContact());
        assertThat(hotelArgumentCaptorValue.getGrade()).isEqualTo(hotelRequest.getGrade());
    }

    @Test
    void createHotelThrowsHotelAlreadyExists(){
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        HotelRequest hotelRequest = HotelRequest.builder().name("Pokoje w miescie").grade(Grade.FIVESTARS).contact(contact).address(address).build();


        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();

        when(hotelRepository.findByName("Pokoje w miescie")).thenReturn(Optional.of(pokojeWMiescie));

        assertThrows(IllegalStateException.class, ()-> hotelService.createHotel(hotelRequest));
    }

    @Test
    void updateHotel() {
        // given
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        HotelRequest hotelRequest = HotelRequest.builder().name("Pokoje w miescie").grade(Grade.FIVESTARS).contact(contact).address(address).build();

        Address UpdateAddress = new Address("Wilcza", "Warszawa", "Mazowieckie", "Polska", "85-021");
        Contact UpdateContact = new Contact("987654321", "pokojenastrychu@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(UpdateAddress).contact(UpdateContact).grade(Grade.ONESTAR).build();
        // when
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(pokojeWMiescie));
        hotelService.updateHotel(1L, hotelRequest);
        // then
        ArgumentCaptor<Hotel> argumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(argumentCaptor.capture());

        Hotel hotelArgumentCaptor = argumentCaptor.getValue();
        assertThat(hotelArgumentCaptor.getHotelId()).isEqualTo(pokojeWMiescie.getHotelId());
        assertThat(hotelArgumentCaptor.getName()).isEqualTo(hotelRequest.getName());
        assertThat(hotelArgumentCaptor.getContact()).isEqualTo(hotelRequest.getContact());
        assertThat(hotelArgumentCaptor.getAddress()).isEqualTo(hotelRequest.getAddress());
        assertThat(hotelArgumentCaptor.getGrade()).isEqualTo(hotelRequest.getGrade());
    }

    @Test
    void updateHotelReturnsNotFound(){
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        HotelRequest hotelRequest = HotelRequest.builder().name("Pokoje w miescie").grade(Grade.FIVESTARS).contact(contact).address(address).build();

        when(hotelRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, ()-> hotelService.updateHotel(1L, hotelRequest));
    }

    @Test
    void deleteHotel() {
        // given
        Address address = new Address("Kujawska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();
        // when
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(pokojeWMiescie));
        hotelService.deleteHotel(1L);
        // then
        ArgumentCaptor<Hotel> argumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).delete(argumentCaptor.capture());

        Hotel hotelArgumentCaptor = argumentCaptor.getValue();
        assertThat(hotelArgumentCaptor.getHotelId()).isEqualTo(pokojeWMiescie.getHotelId());
        assertThat(hotelArgumentCaptor.getName()).isEqualTo(pokojeWMiescie.getName());
        assertThat(hotelArgumentCaptor.getAddress()).isEqualTo(pokojeWMiescie.getAddress());
        assertThat(hotelArgumentCaptor.getContact()).isEqualTo(pokojeWMiescie.getContact());
        assertThat(hotelArgumentCaptor.getGrade()).isEqualTo(pokojeWMiescie.getGrade());
    }

    @Test
    void deleteHotelReturnsNotFound(){
        when(hotelRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, ()-> hotelService.deleteHotel(1L));
    }
}