package pl.siuda.hotel.hotel;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.siuda.hotel.amazonS3bucket.ImageRepo;
import pl.siuda.hotel.amazonS3bucket.filestore.FileStore;
import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.embeddedClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.room.RoomRepository;

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

    @Mock
    RoomRepository roomRepository;

    @Mock
    FileStore fileStore;

    @Mock
    ImageRepo imageRepo;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(hotelService).isNotNull();
        assertThat(roomRepository).isNotNull();
        assertThat(fileStore).isNotNull();
        assertThat(imageRepo).isNotNull();
        assertThat(hotelRepository).isNotNull();
    }

    @Test
    void getAllHotels() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        List<Hotel> hotelList = Arrays.asList(new Hotel(1L, "Pokoje w miescie", address, contact, Grade.ONESTAR),
                new Hotel(2L, "Pokoje na wsi", address, contact, Grade.TWOSTARS),
                new Hotel(3L, "Pokoje nad morzem", address, contact, Grade.THREESTARS),
                new Hotel(4L, "Hotel", address, contact, Grade.FOURSTARS),
                new Hotel(5L, "Hotel w górach", address, contact, Grade.FIVESTARS));
        // when
        when(hotelRepository.findAll()).thenReturn(hotelList);
        List<Hotel> hotels = hotelService.getAllHotels();
        // then
        assertThat(hotels.size()).isEqualTo(5);
    }

    @Test
    void getAllHotelsReturnsEmptyList() {
        // when
        when(hotelRepository.findAll()).thenReturn(Collections.emptyList());
        List<Hotel> hotels = hotelService.getAllHotels();
        // then
        assertThat(hotels.size()).isEqualTo(0);
    }

    @Test
    void getHotelById() {
        // given
        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = new Hotel(1L, "Pokoje w miescie", address, contact, Grade.ONESTAR);
        // when
        when(hotelRepository.findById(1L)).thenReturn(java.util.Optional.of(pokojeWMiescie));
        Hotel hotel = hotelService.NullSafeGetHotelById(1L);
        // then
        assertThat(hotel.getHotel_id()).isEqualTo(pokojeWMiescie.getHotel_id());
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
        assertThrows(NotFoundException.class, ()-> hotelService.NullSafeGetHotelById(1L));
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

        assertThat(hotelArgumentCaptorValue.getName()).isEqualTo(hotelRequest.getName());
        assertThat(hotelArgumentCaptorValue.getStreet()).isEqualTo(hotelRequest.getStreet());
        assertThat(hotelArgumentCaptorValue.getCity()).isEqualTo(hotelRequest.getCity());
        assertThat(hotelArgumentCaptorValue.getState()).isEqualTo(hotelRequest.getState());
        assertThat(hotelArgumentCaptorValue.getCountry()).isEqualTo(hotelRequest.getCountry());
        assertThat(hotelArgumentCaptorValue.getZipcode()).isEqualTo(hotelRequest.getZipcode());
        assertThat(hotelArgumentCaptorValue.getPhoneNumber()).isEqualTo(hotelRequest.getPhoneNumber());
        assertThat(hotelArgumentCaptorValue.getEmail()).isEqualTo(hotelRequest.getEmail());
        assertThat(hotelArgumentCaptorValue.getGrade()).isEqualTo(hotelRequest.getGrade());
    }

    @Test
    void createHotelThrowsHotelAlreadyExists(){
        HotelRequest hotelRequest = new HotelRequest("Pokoje w miescie", "Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie",
                "Polska", "85-021", "123456789", "hotel@gmail.com", Grade.TWOSTARS);

        Address address = new Address("Gdańska", "Bydgoszcz", "Kujawsko-Pomorskie", "Polska", "85-021");
        Contact contact = new Contact("123456789", "hotel@gmail.com");
        Hotel pokojeWMiescie = new Hotel(1L, "Pokoje w miescie", address, contact, Grade.ONESTAR);

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
        Hotel pokojeWMiescie = new Hotel(1L, "Pokoje w miescie", address, contact, Grade.ONESTAR);
        // when
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(pokojeWMiescie));
        hotelService.updateHotel(1L, hotelRequest);
        // then
        ArgumentCaptor<Hotel> argumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).save(argumentCaptor.capture());

        Hotel hotelArgumentCaptor = argumentCaptor.getValue();
        assertThat(hotelArgumentCaptor.getHotel_id()).isEqualTo(pokojeWMiescie.getHotel_id());
        assertThat(hotelArgumentCaptor.getName()).isEqualTo(hotelRequest.getName());
        assertThat(hotelArgumentCaptor.getStreet()).isEqualTo(hotelRequest.getStreet());
        assertThat(hotelArgumentCaptor.getCity()).isEqualTo(hotelRequest.getCity());
        assertThat(hotelArgumentCaptor.getState()).isEqualTo(hotelRequest.getState());
        assertThat(hotelArgumentCaptor.getCountry()).isEqualTo(hotelRequest.getCountry());
        assertThat(hotelArgumentCaptor.getZipcode()).isEqualTo(hotelRequest.getZipcode());
        assertThat(hotelArgumentCaptor.getPhoneNumber()).isEqualTo(hotelRequest.getPhoneNumber());
        assertThat(hotelArgumentCaptor.getEmail()).isEqualTo(hotelRequest.getEmail());
        assertThat(hotelArgumentCaptor.getGrade()).isEqualTo(hotelRequest.getGrade());
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
        Hotel pokojeWMiescie = new Hotel(1L, "Pokoje w miescie", address, contact, Grade.ONESTAR);
        // when
        when(hotelRepository.findById(1L)).thenReturn(Optional.of(pokojeWMiescie));
        hotelService.deleteHotel(1L);
        // then
        ArgumentCaptor<Hotel> argumentCaptor = ArgumentCaptor.forClass(Hotel.class);
        verify(hotelRepository).delete(argumentCaptor.capture());

        Hotel hotelArgumentCaptor = argumentCaptor.getValue();
        assertThat(hotelArgumentCaptor.getHotel_id()).isEqualTo(pokojeWMiescie.getHotel_id());
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