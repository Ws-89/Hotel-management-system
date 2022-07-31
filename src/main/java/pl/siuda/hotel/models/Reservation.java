package pl.siuda.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import pl.siuda.hotel.models.embeddedClasses.BookingDetails;

import javax.persistence.*;

@Entity
@Table(name = "tbl_reservation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "tbl_reservation_sequence", sequenceName = "tbl_reservation_sequence", allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "tbl_reservation_sequence"
    )
    private Long reservationId;

    private BookingDetails bookingDetails = new BookingDetails();
}
