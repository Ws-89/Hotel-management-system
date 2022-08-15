package pl.siuda.hotel.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelWithoutRoomsDTO {
    private UUID hotelId;
    private String name;
    private Address address;
    private Contact contact;
    private Grade grade;
    private String image;
    private boolean enabled;

}
