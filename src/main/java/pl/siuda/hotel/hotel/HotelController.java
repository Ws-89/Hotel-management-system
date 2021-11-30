package pl.siuda.hotel.hotel;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.ImageModel;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/management/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDto> listHotel(){
        List<HotelDto> hotelDtos = hotelService.getAllHotels().stream().map(HotelDto::hotelToDto).collect(Collectors.toList());
        return hotelDtos;
    }

    @GetMapping("{id}")
    public HotelDto getHotelById(@PathVariable("id")Long id){
        return HotelDto.hotelToDto(hotelService.getHotelById(id));
    }

    @PostMapping
    public HotelDto createHotel(@RequestBody HotelRequest hotelRequest){
        return HotelDto.hotelToDto(hotelService.createHotel(hotelRequest));
    }

    @PutMapping("{id}")
    public HotelDto updateHotel(@PathVariable("id")Long id, @RequestBody HotelRequest hotelRequest){
        return HotelDto.hotelToDto(hotelService.updateHotel(id, hotelRequest));
    }

    @DeleteMapping("{id}")
    public void deleteHotel(@PathVariable("id")Long id){
        hotelService.deleteHotel(id);
    }


    @PostMapping(value = "{id}/images/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadHotelImage(@PathVariable("id") Long hotel_id, @RequestParam("file")MultipartFile file){
        hotelService.uploadHotelImage(hotel_id, file);
    }

    @GetMapping(value = "{id}/images/download")
    public ImageModel downloadHotelProfileImage(@PathVariable("id") Long hotel_id) {
         ImageModel imageModel = new ImageModel(hotelService.downloadHotelProfileImage(hotel_id));
         return imageModel;
    }



}
