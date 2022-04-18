package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Reservation {

    @Id
    private Long reservationId;
    private long hotelId;
    private String hotelName;
    private String city;
    private Grade grade;
    private String image;
    private long roomId;
    private RoomType roomType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public Reservation() {
    }

    public Reservation(long hotelId,
                       String hotelName,
                       String city,
                       Grade grade,
                       String image,
                       long roomId,
                       RoomType roomType,
                       LocalDateTime startDate,
                       LocalDateTime endDate,
                       BigDecimal price) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.grade = grade;
        this.image = image;
        this.roomId = roomId;
        this.roomType = roomType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public Long getReservationId() {
        return reservationId;
    }

    public void setReservationId(Long reservationId) {
        this.reservationId = reservationId;
    }

    public long getHotelId() {
        return hotelId;
    }

    public void setHotelId(long hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
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

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
