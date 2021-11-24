package pl.siuda.hotel.hotel;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.room.RoomRepo;
import pl.siuda.hotel.exception.NotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;

    public HotelService(HotelRepo hotelRepo, RoomRepo roomRepo) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
    }


    public List<Hotel> getAllHotels(){
        return StreamSupport.stream(hotelRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Hotel getHotelById(Long id){
        return hotelRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
    }

    public Hotel createHotel(HotelRequest hotelRequest){
        Optional<Hotel> ifExists = hotelRepo.findByName(hotelRequest.getName());
        if(ifExists.isPresent()) {
            throw new IllegalStateException("Hotel already exists");
        }
        Hotel hotel = new Hotel();
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepo.save(hotel);
    }

    public Hotel updateHotel(Long id, HotelRequest hotelRequest){
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepo.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = hotelRepo.findById(id).orElseThrow(()-> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotelRepo.delete(hotel);
        }



}

