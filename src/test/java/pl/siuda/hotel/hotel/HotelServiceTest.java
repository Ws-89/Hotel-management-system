package pl.siuda.hotel.hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.siuda.hotel.dto.HotelDto;
import pl.siuda.hotel.dto.HotelRequest;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.repositories.HotelRepository;
import pl.siuda.hotel.services.HotelService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        List<Hotel> hotelList = Arrays.asList(
                Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build(),
                Hotel.builder().hotelId(2L).name("Pokoje na wsi").address(address).contact(contact).grade(Grade.TWOSTARS).build(),
                Hotel.builder().hotelId(3L).name("Pokoje nad morzem").address(address).contact(contact).grade(Grade.THREESTARS).build(),
                Hotel.builder().hotelId(4L).name("Hotel").address(address).contact(contact).grade(Grade.FOURSTARS).build(),
                Hotel.builder().hotelId(5L).name("Hotel w górach").address(address).contact(contact).grade(Grade.FIVESTARS).build());
        // when
        when(hotelRepository.findAll()).thenReturn(hotelList);
        List<HotelDto> hotels = hotelService.getAllHotels();
        // then
        assertThat(hotels.size()).isEqualTo(5);
    }

    @Test
    void getAllHotelsReturnsEmptyList() {
        // when
        when(hotelRepository.findAll()).thenReturn(Collections.emptyList());
        List<HotelDto> hotels = hotelService.getAllHotels();
        // then
        assertThat(hotels.size()).isEqualTo(0);
    }

    @Test
    void getHotelById() {
        // given
        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();
        // when
        when(hotelRepository.findById(1L)).thenReturn(java.util.Optional.of(pokojeWMiescie));
        HotelDto hotel = hotelService.getHotelById(1L);
        // then
        assertThat(hotel.getHotelId()).isEqualTo(pokojeWMiescie.getHotelId());
        assertThat(hotel.getHotelName()).isEqualTo(pokojeWMiescie.getName());
//        assertThat(hotel.getAddress()).isEqualTo(pokojeWMiescie.getAddress());
//        assertThat(hotel.getContact()).isEqualTo(pokojeWMiescie.getContact());
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
        HotelRequest hotelRequest = new HotelRequest("Pokoje w miescie", "Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie",
                "Polska", "85-021", "123456789", "hotel@gmail.com", grade );
        // when
        when(hotelRepository.findByName("Pokoje w miescie")).thenReturn(Optional.empty());
        hotelService.createHotel(hotelRequest);
        // then
        ArgumentCaptor<Hotel> hotelArgumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(hotelArgumentCaptor.capture());

        Hotel hotelArgumentCaptorValue = hotelArgumentCaptor.getValue();

        assertThat(hotelArgumentCaptorValue.getName()).isEqualTo(hotelRequest.getHotelName());
        assertThat(hotelArgumentCaptorValue.getStreet()).isEqualTo(hotelRequest.getHotelStreet());
        assertThat(hotelArgumentCaptorValue.getCity()).isEqualTo(hotelRequest.getHotelCity());
        assertThat(hotelArgumentCaptorValue.getState()).isEqualTo(hotelRequest.getHotelState());
        assertThat(hotelArgumentCaptorValue.getCountry()).isEqualTo(hotelRequest.getHotelCountry());
        assertThat(hotelArgumentCaptorValue.getZipcode()).isEqualTo(hotelRequest.getHotelZipCode());
        assertThat(hotelArgumentCaptorValue.getPhoneNumber()).isEqualTo(hotelRequest.getHotelPhone());
        assertThat(hotelArgumentCaptorValue.getEmail()).isEqualTo(hotelRequest.getHotelEmail());
        assertThat(hotelArgumentCaptorValue.getGrade()).isEqualTo(hotelRequest.getHotelGrade());
    }

    @Test
    void createHotelThrowsHotelAlreadyExists(){
        HotelRequest hotelRequest = new HotelRequest("Pokoje w miescie", "Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie",
                "Polska", "85-021", "123456789", "hotel@gmail.com", Grade.TWOSTARS);

        Address address = Address.builder().street("Gdańska").city("Bydgoszcz").state("Kujawsko-Pomorskie").country("Polska").zipCode("85-021").build();
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();

        when(hotelRepository.findByName("Pokoje w miescie")).thenReturn(Optional.of(pokojeWMiescie));

        assertThrows(IllegalStateException.class, ()-> hotelService.createHotel(hotelRequest));
    }

    @Test
    void updateHotel() {
        // given
        HotelRequest hotelRequest = new HotelRequest("Pokoje w miescie", "Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie",
                "Polska", "85-021", "123456789", "hotel@gmail.com", Grade.TWOSTARS);

        Address address = new Address("Wilcza", "Warszawa", "Mazowieckie", "Polska", "85-021");
        Contact contact = new Contact("987654321", "pokojenastrychu@gmail.com");
        Hotel pokojeWMiescie = Hotel.builder().hotelId(1L).name("Pokoje w miescie").address(address).contact(contact).grade(Grade.ONESTAR).build();
        // when
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(pokojeWMiescie));
        hotelService.updateHotel(1L, hotelRequest);
        // then
        ArgumentCaptor<Hotel> argumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(argumentCaptor.capture());

        Hotel hotelArgumentCaptor = argumentCaptor.getValue();
        assertThat(hotelArgumentCaptor.getHotelId()).isEqualTo(pokojeWMiescie.getHotelId());
        assertThat(hotelArgumentCaptor.getName()).isEqualTo(hotelRequest.getHotelName());
        assertThat(hotelArgumentCaptor.getStreet()).isEqualTo(hotelRequest.getHotelStreet());
        assertThat(hotelArgumentCaptor.getCity()).isEqualTo(hotelRequest.getHotelCity());
        assertThat(hotelArgumentCaptor.getState()).isEqualTo(hotelRequest.getHotelState());
        assertThat(hotelArgumentCaptor.getCountry()).isEqualTo(hotelRequest.getHotelCountry());
        assertThat(hotelArgumentCaptor.getZipcode()).isEqualTo(hotelRequest.getHotelZipCode());
        assertThat(hotelArgumentCaptor.getPhoneNumber()).isEqualTo(hotelRequest.getHotelPhone());
        assertThat(hotelArgumentCaptor.getEmail()).isEqualTo(hotelRequest.getHotelEmail());
        assertThat(hotelArgumentCaptor.getGrade()).isEqualTo(hotelRequest.getHotelGrade());
    }

    @Test
    void updateHotelReturnsNotFound(){
        HotelRequest hotelRequest = new HotelRequest("Pokoje w miescie", "Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie",
                "Polska", "85-021", "123456789", "hotel@gmail.com", Grade.TWOSTARS);
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
        assertThat(hotelArgumentCaptor.getStreet()).isEqualTo(pokojeWMiescie.getStreet());
        assertThat(hotelArgumentCaptor.getCity()).isEqualTo(pokojeWMiescie.getCity());
        assertThat(hotelArgumentCaptor.getState()).isEqualTo(pokojeWMiescie.getState());
        assertThat(hotelArgumentCaptor.getCountry()).isEqualTo(pokojeWMiescie.getCountry());
        assertThat(hotelArgumentCaptor.getZipcode()).isEqualTo(pokojeWMiescie.getZipcode());
        assertThat(hotelArgumentCaptor.getPhoneNumber()).isEqualTo(pokojeWMiescie.getPhoneNumber());
        assertThat(hotelArgumentCaptor.getEmail()).isEqualTo(pokojeWMiescie.getEmail());
        assertThat(hotelArgumentCaptor.getGrade()).isEqualTo(pokojeWMiescie.getGrade());
    }

    @Test
    void deleteHotelReturnsNotFound(){
        when(hotelRepository.findById(1L)).thenThrow(NotFoundException.class);

        assertThrows(NotFoundException.class, ()-> hotelService.deleteHotel(1L));
    }
}