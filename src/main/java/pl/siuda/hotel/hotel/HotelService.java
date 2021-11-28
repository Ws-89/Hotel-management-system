package pl.siuda.hotel.hotel;

import org.apache.http.entity.ContentType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.Image;
import pl.siuda.hotel.amazonS3bucket.ImageRepo;
import pl.siuda.hotel.amazonS3bucket.bucket.BucketName;
import pl.siuda.hotel.amazonS3bucket.filestore.FileStore;
import pl.siuda.hotel.room.RoomRepo;
import pl.siuda.hotel.exception.NotFoundException;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class HotelService {

    private final HotelRepo hotelRepo;
    private final RoomRepo roomRepo;
    private final FileStore fileStore;
    private final ImageRepo imageRepo;

    public HotelService(HotelRepo hotelRepo, RoomRepo roomRepo, ImageRepo imageRepo, FileStore fileStore, ImageRepo imageRepo1) {
        this.hotelRepo = hotelRepo;
        this.roomRepo = roomRepo;
        this.fileStore = fileStore;
        this.imageRepo = imageRepo1;
    }


    public List<Hotel> getAllHotels(){
        return StreamSupport.stream(hotelRepo.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Hotel getHotelById(Long id){
        return getHotelByIdOrThrowException(id);
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
        Hotel hotel = getHotelByIdOrThrowException(id);
        hotelRequest.copyRequestToEntity(hotel);
        return hotelRepo.save(hotel);
    }

    public void deleteHotel(Long id){
        Hotel hotel = getHotelByIdOrThrowException(id);
        hotelRepo.delete(hotel);
        }


    public void uploadHotelImage(Long hotel_id, MultipartFile file) {
        isFileEmpty(file);

        isAnImage(file);

        Hotel hotel = getHotelByIdOrThrowException(hotel_id);

        Map<String, String> metadata = extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());

            Image image = new Image(fileName);

            hotel.setImage(image);
            hotelRepo.save(hotel);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadHotelProfileImage(Long hotel_id) {
        Hotel hotel = getHotelByIdOrThrowException(hotel_id);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());

       return hotel.getImageLink().map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }

    private Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Size", String.valueOf(file.getSize()));
        return metadata;
    }

    private Hotel getHotelByIdOrThrowException(Long hotel_id) {
        return hotelRepo.findById(hotel_id).orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", hotel_id)));
    }

    private void isAnImage(MultipartFile file) {
        if(!Arrays.asList(ContentType.IMAGE_JPEG.getMimeType(), ContentType.IMAGE_PNG.getMimeType(), ContentType.IMAGE_GIF.getMimeType()).contains(file.getContentType()))
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
    }

    private void isFileEmpty(MultipartFile file) {
        if(file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }
    }
}

