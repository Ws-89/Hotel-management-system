package pl.siuda.hotel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.models.HttpResponse;
import pl.siuda.hotel.requests.HotelRequest;
import pl.siuda.hotel.services.HotelService;

import java.util.Map;
import java.util.Optional;

import static java.time.LocalDateTime.*;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/hotel-management/hotels")
public class HotelController {

    private final HotelService hotelService;

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/list")
    public ResponseEntity<HttpResponse> getHotelsByCity(@RequestParam Optional<Integer> page,@RequestParam Optional<Integer> size){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("page", hotelService.getHotelsByCity(page.orElse(0), size.orElse(10))))
                        .message("hotel list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<HttpResponse> getHotelById(@PathVariable("id")Long id){
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .data(Map.of("object", hotelService.getHotelById(id)))
                        .message("hotel list retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<HttpResponse> createHotel(@RequestBody HotelRequest hotelRequest){
        hotelService.createHotel(hotelRequest);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("hotel created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @PutMapping("{id}")
    public ResponseEntity<HttpResponse> updateHotel(@PathVariable("id")Long id, @RequestBody HotelRequest hotelRequest){
        hotelService.updateHotel(id, hotelRequest);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("hotel updated")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpResponse> deleteHotel(@PathVariable("id")Long id){
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("hotel deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping("{id}/images/upload")
    public ResponseEntity<HttpResponse> uploadImage(@PathVariable("id")Long id, @RequestParam("file") MultipartFile file){
        hotelService.uploadImage(id, file);
        return ResponseEntity.ok().body(
                HttpResponse.builder().timeStamp(now().toString())
                        .message("image uploaded")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }


}
