package pl.siuda.hotel.reservation;

import org.springframework.data.annotation.Id;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.enums.RoomType;


import java.math.BigDecimal;
import java.time.LocalDateTime;


public class Availability {

    @Id
    private Long availabilityId;
    private Long hotelId;
    private String hotelName;
    private String city;
    private Grade grade;
    private String image;
    private Long roomId;
    private Long roomGroupId;
    private RoomType roomType;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal price;

    public Availability() {
    }

    public Availability(Long availabilityId,
                        Long hotelId,
                        String hotelName,
                        String city,
                        Grade grade,
                        String image,
                        Long roomId,
                        Long roomGroupId,
                        RoomType roomType,
                        String description,
                        LocalDateTime startDate,
                        LocalDateTime endDate,
                        BigDecimal price) {
        this.availabilityId = availabilityId;
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.city = city;
        this.grade = grade;
        this.image = image;
        this.roomId = roomId;
        this.roomGroupId = roomGroupId;
        this.roomType = roomType;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getAvailabilityId() {
        return availabilityId;
    }

    public void setAvailabilityId(Long availabilityId) {
        this.availabilityId = availabilityId;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotelId = hotel_id;
    }

    public void setRoom_id(Long room_id) {
        this.roomId = room_id;
    }

    public Long getRoomGroupId() {
        return roomGroupId;
    }

    public void setRoomGroupId(Long roomGroupId) {
        this.roomGroupId = roomGroupId;
    }

    @Override
    public String toString() {
        return "Availability{" +
                "availability_id=" + availabilityId +
                ", hotel_id=" + hotelId +
                ", hotel_name='" + hotelName + '\'' +
                ", city='" + city + '\'' +
                ", grade=" + grade +
                ", image='" + image + '\'' +
                ", room_id=" + roomId +
                ", roomType=" + roomType +
                ", from_date=" + startDate +
                ", to_date=" + endDate +
                ", price=" + price +
                '}';
    }
}
