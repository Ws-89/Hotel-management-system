package pl.siuda.hotel.hotel;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.Image;
import pl.siuda.hotel.amazonS3bucket.ImageRepo;
import pl.siuda.hotel.amazonS3bucket.bucket.BucketName;
import pl.siuda.hotel.amazonS3bucket.filestore.FileStore;
import pl.siuda.hotel.room.RoomRepository;
import pl.siuda.hotel.exception.NotFoundException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final FileStore fileStore;
    private final ImageRepo imageRepo;

    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository, ImageRepo imageRepo, FileStore fileStore, ImageRepo imageRepo1) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.fileStore = fileStore;
        this.imageRepo = imageRepo1;
    }

    public List<Hotel> getAllHotels(){
        return StreamSupport.stream(hotelRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Hotel NullSafeGetHotelById(Long hotel_id) {
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
        Hotel hotel = NullSafeGetHotelById(id);
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepository.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = NullSafeGetHotelById(id);
        hotelRepository.delete(hotel);
        }


    public void uploadHotelImage(Long hotel_id, MultipartFile file) {
        fileStore.isFileEmpty(file);

        fileStore.isAnImage(file);

        Hotel hotel = NullSafeGetHotelById(hotel_id);

        Map<String, String> metadata = fileStore.extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());

            Image image = new Image(fileName);

            hotel.setImage(image);
            hotelRepository.save(hotel);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadHotelProfileImage(Long hotel_id) {
        Hotel hotel = NullSafeGetHotelById(hotel_id);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());

       return hotel.getImageLink().map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }



}

