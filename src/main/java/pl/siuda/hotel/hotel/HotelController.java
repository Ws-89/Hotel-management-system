package pl.siuda.hotel.hotel;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hotel-management/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDto> listHotel(){
        return hotelService.getAllHotels();

    }

    @GetMapping("{id}")
    public HotelDto getHotelById(@PathVariable("id")Long id){
        return hotelService.getHotelById(id);
    }

    @PostMapping
    public void createHotel(@RequestBody HotelRequest hotelRequest){
        hotelService.createHotel(hotelRequest);
    }

    @PutMapping("{id}")
    public void updateHotel(@PathVariable("id")Long id, @RequestBody HotelRequest hotelRequest){
        hotelService.updateHotel(id, hotelRequest);
    }

    @DeleteMapping("{id}")
    public void deleteHotel(@PathVariable("id")Long id){
        hotelService.deleteHotel(id);
    }

    @PostMapping("{id}/images/upload")
    public void uploadImage(@PathVariable("id")Long id, @RequestParam("file") MultipartFile file){
        hotelService.uploadImage(id, file);
    }


}
