package pl.siuda.hotel.dto;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.models.Hotel;

import java.io.Serializable;

public class HotelDto implements Serializable {
    private Long hotelId;
    private String hotelName;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phoneNumber;
    private String email;
    private Grade grade;
    private String image;

    public static HotelDto hotelToDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.hotelId = hotel.getHotelId();
        hotelDto.hotelName = hotel.getName();
        hotelDto.street = hotel.getStreet();
        hotelDto.city = hotel.getCity();
        hotelDto.state = hotel.getState();
        hotelDto.country = hotel.getCountry();
        hotelDto.zipCode = hotel.getZipcode();
        hotelDto.phoneNumber = hotel.getPhoneNumber();
        hotelDto.email = hotel.getEmail();
        hotelDto.grade = hotel.getGrade();
        hotelDto.image = hotel.getImageUrl();
        return hotelDto;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getImage() { return image; }
}
