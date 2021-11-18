package pl.siuda.hotel.hotel;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.room.RoomRepo;
import pl.siuda.hotel.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class HotelService implements IHotelWrite, IHotelRead {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;

    public HotelService(HotelRepo hotelRepo, RoomRepo roomRepo) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
    }


    public List<Hotel> getAllHotels(){
        return (List<Hotel>) hotelRepo.findAll();
    }

    public Hotel getHotelById(Long id){
        return hotelRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
    }

    public Hotel createHotel(Hotel hotel){
        Optional<Hotel> ifExists = hotelRepo.findByName(hotel.getName());
        if(ifExists.isPresent()) {
            throw new IllegalStateException("Hotel already exists");
        }
        return hotelRepo.save(hotel);
//        if(result == null){
//            throw new IllegalStateException("Something went wrong");
//        }
    }

    public Hotel updateHotel(Long id, Hotel hotelDetails){
        Hotel hotel = hotelRepo.findById(id)
                .orElseThrow(()-> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotel.updateDetails(hotelDetails);
        return hotelRepo.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = hotelRepo.findById(id).get();
        if(hotel != null) {
            hotelRepo.delete(hotel);
        }else {throw new NotFoundException(String.format("Hotel with id %s not found", id));

            }

        }



}

