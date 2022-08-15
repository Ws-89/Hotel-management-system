package pl.siuda.hotel.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import pl.siuda.hotel.models.enums.RoomType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tbl_room")
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.roomWReservationsWoGuest", attributeNodes = {
                @NamedAttributeNode(value = "reservations")
        })
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "roomId")
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id")
    private UUID roomId;
    private Long roomNumber;
    private String description;
    @Enumerated
    private RoomType roomType;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name ="hotel_id", referencedColumnName = "hotel_id")
    private Hotel hotel;
    @JsonIgnore
    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private Set<Reservation> reservations;
    private BigDecimal price;
    private Boolean enabled;

    public Room(UUID roomId, Long roomNumber, String description, RoomType roomType, Hotel hotel, Set<Reservation> reservations, BigDecimal price, Boolean enabled) {
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.description = description;
        this.roomType = roomType;
        this.hotel = hotel;
        this.reservations = reservations;
        this.price = price;
        this.enabled = enabled;
    }

    public Room() {
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumer(Long roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getRoomId() {
        return roomId;
    }

    public void setRoomId(UUID roomId) {
        this.roomId = roomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void addReservation(Reservation reservation) {
        if(this.reservations == null){
            this.reservations = new HashSet<>();
        }
        if(!this.reservations.add(reservation))
            throw new IllegalArgumentException("Something went wrong. Cannot add reservation");

        reservation.setRoom(this);
    }

    public void removeReservation(Reservation reservation) {
        if(this.reservations == null){
            this.reservations = new HashSet<>();
        }
        if(!this.reservations.remove(reservation))
            throw new IllegalArgumentException("Something went wrong. Cannot add reservation");

        reservation.setRoom(null);
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_id=" + roomId +
                '}';
    }
}
