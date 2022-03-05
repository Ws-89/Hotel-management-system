package pl.siuda.hotel.imagesManagement;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.Image;
import pl.siuda.hotel.amazonS3bucket.ImageRepo;
import pl.siuda.hotel.amazonS3bucket.bucket.BucketName;
import pl.siuda.hotel.amazonS3bucket.filestore.FileStore;
import pl.siuda.hotel.hotel.Hotel;
import pl.siuda.hotel.hotel.HotelService;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ImagesService {

    private final FileStore fileStore;
    private final ImageRepo imageRepo;
    private final HotelService hotelService;

    public ImagesService(FileStore fileStore, ImageRepo imageRepo, HotelService hotelService) {
        this.fileStore = fileStore;
        this.imageRepo = imageRepo;
        this.hotelService = hotelService;
    }

    public void uploadHotelImage(Long hotel_id, MultipartFile file) {
        fileStore.isFileEmpty(file);

        fileStore.isAnImage(file);

        Hotel hotel = hotelService.NullSafeGetHotelById(hotel_id);

        Map<String, String> metadata = fileStore.extractMetadata(file);

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());
        String fileName = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStore.save(path, fileName, Optional.of(metadata), file.getInputStream());

            Image image = new Image(fileName);

            hotel.setImage(image);
            hotelService.updateHotel(hotel);

        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public byte[] downloadHotelProfileImage(Long hotel_id) {
        Hotel hotel = hotelService.NullSafeGetHotelById(hotel_id);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), hotel.getHotel_id());

        return hotel.getImageLink().map(key -> fileStore.download(path, key))
                .orElse(new byte[0]);
    }
}
