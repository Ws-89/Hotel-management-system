package pl.siuda.hotel.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import pl.siuda.hotel.models.embeddedClasses.BookingDetails;


@Data
@NoArgsConstructor
public class Availability {

    @Id
    private Long availabilityId;
    private String description;
    private Long roomGroupId;

    private BookingDetails bookingDetails = new BookingDetails();



}
