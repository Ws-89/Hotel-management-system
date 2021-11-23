package pl.siuda.hotel.hotel;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin/management/hotels")
public class HotelController {

    private final IHotelWrite hotelWriteService;
    private final IHotelRead hotelReadService;

    public HotelController(HotelService hotelWriteService, IHotelRead hotelReadService) {
        this.hotelWriteService = hotelWriteService;
        this.hotelReadService = hotelReadService;
    }

    @GetMapping
    public List<Hotel> listHotel(){
        return hotelReadService.getAllHotels();
    }

    @GetMapping("{id}")
    public Hotel getHotelById(@PathVariable("id")Long id){
        return hotelReadService.getHotelById(id);
    }

    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel){
        return hotelWriteService.createHotel(hotel);
    }

    @PutMapping("{id}")
    public Hotel updateHotel(@PathVariable("id")Long id, @RequestBody Hotel hotel){
        return hotelWriteService.updateHotel(id, hotel);
    }

    @DeleteMapping("{id}")
    public void deleteHotel(@PathVariable("id")Long id){
        hotelWriteService.deleteHotel(id);
    }


}
