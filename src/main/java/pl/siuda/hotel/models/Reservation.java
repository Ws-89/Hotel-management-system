package pl.siuda.hotel.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import pl.siuda.hotel.models.embeddedClasses.Address;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_reservation")
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.reservationWithGuest", attributeNodes = {
                @NamedAttributeNode(value = "guest")
        }),
        @NamedEntityGraph(name = "graph.reservationWithRoom", attributeNodes = {
                @NamedAttributeNode(value = "room")
        })
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "reservationId")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "reservation_id")
    private UUID reservationId;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", referencedColumnName = "room_id")
    private Room room;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String guestName;
    private String guestLastName;
    private String email;
    private String requestMessage;
    @Embedded
    private Address guestAddress;
    private BigDecimal price;

    private ReservationStatus reservationStatus;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_id", referencedColumnName = "guest_id")
    private Guest guest;


    public Reservation() {
    }

    public Reservation(UUID reservationId, Room room, LocalDateTime startDate, LocalDateTime endDate, String guestName, String guestLastName, String email, String requestMessage, Address guestAddress, BigDecimal price, ReservationStatus reservationStatus, Guest guest) {
        this.reservationId = reservationId;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestName = guestName;
        this.guestLastName = guestLastName;
        this.email = email;
        this.requestMessage = requestMessage;
        this.guestAddress = guestAddress;
        this.price = price;
        this.reservationStatus = reservationStatus;
        this.guest = guest;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public void setReservationId(UUID reservationId) {
        this.reservationId = reservationId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestLastName() {
        return guestLastName;
    }

    public void setGuestLastName(String guestLastName) {
        this.guestLastName = guestLastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public Address getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(Address guestAddress) {
        this.guestAddress = guestAddress;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ReservationStatus getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(ReservationStatus reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
