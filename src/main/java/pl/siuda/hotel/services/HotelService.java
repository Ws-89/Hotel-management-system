package pl.siuda.hotel.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.dto.HotelDto;
import pl.siuda.hotel.dto.HotelRequest;
import pl.siuda.hotel.imageService.ImageService;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.repositories.HotelRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ImageService imageService;


    public HotelService(HotelRepository hotelRepository, ImageService imageService) {
        this.hotelRepository = hotelRepository;
        this.imageService = imageService;
    }

    public List<HotelDto> getAllHotels(){
        return StreamSupport.stream(hotelRepository.findAll().spliterator(), false)
                .map(hotel -> HotelDto.hotelToDto(hotel))
                .collect(Collectors.toList());
    }

    public HotelDto getHotelById(Long hotel_id) {
        return hotelRepository.findById(hotel_id)
                .map(hotel -> HotelDto.hotelToDto(hotel))
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", hotel_id)));
    }

    public void createHotel(HotelRequest hotelRequest){
        Optional<Hotel> ifExists = hotelRepository.findByName(hotelRequest.getHotelName());
        if(ifExists.isPresent()) {
            throw new IllegalStateException("Hotel already exists");
        }
        Hotel hotel = new Hotel();
        hotelRequest.copyRequestToEntity(hotel);
        hotelRepository.save(hotel);
    }

    public void updateHotel(Long id, HotelRequest hotelRequest){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotelRequest.copyRequestToEntity(hotel);
        hotelRepository.save(hotel);
    }

    public void updateHotel(Hotel hotel){ hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotelRepository.delete(hotel);
    }

    public void uploadImage(Long id, MultipartFile file){
        Hotel hotel = hotelRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", id)));
        hotel.setImageUrl(imageService.uploadFile(file));
        hotelRepository.save(hotel);
    }

}

