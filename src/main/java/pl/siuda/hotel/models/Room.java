package pl.siuda.hotel.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.enums.RoomType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room implements Serializable {

    @Id
    @SequenceGenerator(
            name = "tbl_room_sequence", sequenceName = "tbl_room_sequence", allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "tbl_room_sequence"
    )
    @Column(name = "room_id")
    private Long roomId;
    private String description;
    private RoomType roomType;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name ="hotel_id", referencedColumnName = "hotel_id")
    private Hotel hotel;
    @OneToMany(mappedBy = "room")
    private Set<Reservation> reservations;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
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
