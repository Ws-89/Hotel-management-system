package pl.siuda.hotel.hotel;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.imageService.ImageService;
import pl.siuda.hotel.room.RoomRepository;
import pl.siuda.hotel.exception.NotFoundException;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final ImageService imageService;


    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository, ImageService imageService) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.imageService = imageService;
    }

    public List<Hotel> getAllHotels(){
        return StreamSupport.stream(hotelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Hotel nullSafeGetHotelById(Long hotel_id) {
        return hotelRepository.findById(hotel_id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", hotel_id)));
    }

    public Hotel createHotel(HotelRequest hotelRequest){
        Optional<Hotel> ifExists = hotelRepository.findByName(hotelRequest.getName());
        if(ifExists.isPresent()) {
            throw new IllegalStateException("Hotel already exists");
        }
        Hotel hotel = new Hotel();
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Long id, HotelRequest hotelRequest){
        Hotel hotel = nullSafeGetHotelById(id);
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(Hotel hotel){
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = nullSafeGetHotelById(id);
        hotelRepository.delete(hotel);
    }

    public Hotel uploadImage(Long id, MultipartFile file){
        Hotel hotel = nullSafeGetHotelById(id);
        hotel.setImageUrl(imageService.uploadFile(file));
        return hotelRepository.save(hotel);
    }

}

