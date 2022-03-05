package pl.siuda.hotel.imagesManagement;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.ImageModel;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/images/")
public class ImagesController {

    private final ImagesService imagesService;

    public ImagesController(ImagesService imagesService) {
        this.imagesService = imagesService;
    }

    @PostMapping(value = "{id}/images/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadHotelImage(@PathVariable("id") Long hotel_id, @RequestParam("file") MultipartFile file){
        imagesService.uploadHotelImage(hotel_id, file);
    }

    @GetMapping(value = "{id}/images/download")
    public ImageModel downloadHotelProfileImage(@PathVariable("id") Long hotel_id) {
        ImageModel imageModel = new ImageModel(imagesService.downloadHotelProfileImage(hotel_id));
        return imageModel;
    }


}
