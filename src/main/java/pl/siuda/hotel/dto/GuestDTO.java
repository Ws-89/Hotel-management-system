package pl.siuda.hotel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.models.Reservation;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuestDTO implements Serializable {
    private UUID guestId;
    private String firstName;
    private String lastName;
    private String email;
    private Address address;
    private String phoneNumber;
    private List<ReservationDTO> reservations;





}
