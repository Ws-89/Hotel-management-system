package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Reservation {

    @Id
    private Long reservation_id;
    private boolean confirmed = false;
    private String email;
    private long hotel_id;
    private String hotel_name;
    private String city;
    private Grade grade;
    private String image;
    private long room_id;
    private RoomType roomType;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private BigDecimal price;

    public Reservation(boolean confirmed,
                       String email,
                       long hotel_id,
                       String hotel_name,
                       String city,
                       Grade grade,
                       String image,
                       long room_id,
                       RoomType roomType,
                       LocalDateTime from_date,
                       LocalDateTime to_date,
                       BigDecimal price) {
        this.confirmed = confirmed;
        this.email = email;
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.city = city;
        this.grade = grade;
        this.image = image;
        this.room_id = room_id;
        this.roomType = roomType;
        this.from_date = from_date;
        this.to_date = to_date;
        this.price = price;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Reservation() {
    }

    public LocalDateTime getFrom_date() {
        return from_date;
    }

    public void setFrom_date(LocalDateTime from_date) {
        this.from_date = from_date;
    }

    public LocalDateTime getTo_date() {
        return to_date;
    }

    public void setTo_date(LocalDateTime to_date) {
        this.to_date = to_date;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Long getId() {
        return reservation_id;
    }

    public void setId(Long id) {
        this.reservation_id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRoom_id(long room_id) {
        this.room_id = room_id;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "reservationNo=" +  +
                '}';
    }
}
