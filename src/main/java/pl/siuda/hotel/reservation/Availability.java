package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Availability {

    @Id
    private Long availability_id;
    private Long hotel_id;
    private String hotel_name;
    private String city;
    private Grade grade;
    private String image;
    private Long room_id;
    private Long room_group_id;
    private RoomType roomType;
    private LocalDateTime from_date;
    private LocalDateTime to_date;
    private BigDecimal price;

    public Availability() {
    }

    public Availability(
                        long hotel_id,
                        String hotel_name,
                        String city,
                        Grade grade,
                        String image,
                        long room_id,
                        Long room_group_id,
                        RoomType roomType,
                        LocalDateTime from_date,
                        LocalDateTime to_date,
                        BigDecimal price) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.city = city;
        this.grade = grade;
        this.image = image;
        this.room_id = room_id;
        this.room_group_id = room_group_id;
        this.roomType = roomType;
        this.from_date = from_date;
        this.to_date = to_date;
        this.price = price;
    }

    public long getRoom_id() {
        return room_id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getAvailability_id() {
        return availability_id;
    }

    public void setAvailability_id(Long availability_id) {
        this.availability_id = availability_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getRoom_group_id() {
        return room_group_id;
    }

    public void setRoom_group_id(Long room_group_id) {
        this.room_group_id = room_group_id;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availability_id=" + availability_id +
                ", hotel_id=" + hotel_id +
                ", hotel_name='" + hotel_name + '\'' +
                ", city='" + city + '\'' +
                ", grade=" + grade +
                ", image='" + image + '\'' +
                ", room_id=" + room_id +
                ", roomType=" + roomType +
                ", from_date=" + from_date +
                ", to_date=" + to_date +
                ", price=" + price +
                '}';
    }
}
