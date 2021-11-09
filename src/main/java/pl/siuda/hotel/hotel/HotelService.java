package pl.siuda.hotel.hotel;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.room.Room;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    public List<Hotel> getAllHotels(){
        return hotelRepository.findAll();
    }

    public Hotel getHotelById(Long id){
        return hotelRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
    }

    public void createHotel(Hotel hotel){
        if(hotelRepository.findByName(hotel.getName()).isPresent()){
            throw new IllegalStateException("Hotel already exists");
        }
        int result = hotelRepository.create(hotel);
        if(result != 1){
            throw new IllegalStateException("Something went wrong");
        }
    }

    public int updateHotel(Long id, Hotel hotelDetails){
        Hotel hotel = hotelRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Hotel with id %s not found", id)));
        return hotelRepository.update(hotelDetails, id);
    }

    public void deleteHotel(Long id){
        Optional<Hotel> hotels = hotelRepository.findById(id);
        hotels.ifPresentOrElse(hotel -> {
            int result = hotelRepository.delete(id);
            if(result != 1){
                throw new IllegalStateException("could not delete hotel");
            }
            }, () -> {
            throw new NotFoundException(String.format("Hotel with id %s not found", id));
        });
    }
}
