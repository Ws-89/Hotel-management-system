package pl.siuda.hotel.hotel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("api/v1/hotel")
public class HotelController {

    private final HotelCreationInterface hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<Hotel> listHotel(){
        return hotelService.getAllHotels();
    }

    @GetMapping("{id}")
    public Hotel getHotelById(@PathVariable("id")Long id){
        return hotelService.getHotelById(id);
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelService.createHotel(hotel);
    }

    @PutMapping("{id}")
    public Hotel updateHotel(@PathVariable("id")Long id, @RequestBody Hotel hotel){
        return hotelService.updateHotel(id, hotel);
    }

    @DeleteMapping("{id}")
    public void deleteHotel(@PathVariable("id")Long id){
        hotelService.deleteHotel(id);
    }


}
