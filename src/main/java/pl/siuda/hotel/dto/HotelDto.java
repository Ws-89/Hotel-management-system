package pl.siuda.hotel.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.models.Hotel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto {
    private Long hotelId;
    private String name;
    private Address address;
    private Contact contact;
    private Grade grade;
    private String image;

    public static HotelDto hotelToDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.hotelId = hotel.getHotelId();
        hotelDto.name = hotel.getName();
        hotelDto.contact = hotel.getContact();
        hotelDto.address = hotel.getAddress();
        hotelDto.grade = hotel.getGrade();
        hotelDto.image = hotel.getImage();
        return hotelDto;
    }


}
