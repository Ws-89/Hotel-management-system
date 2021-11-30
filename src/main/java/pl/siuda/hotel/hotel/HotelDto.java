package pl.siuda.hotel.hotel;

import pl.siuda.hotel.amazonS3bucket.Image;
import pl.siuda.hotel.amazonS3bucket.ImageModel;
import pl.siuda.hotel.enums.Grade;

import java.io.Serializable;

public class HotelDto implements Serializable {
    private Long hotel_id;
    private String name;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phoneNumber;
    private String email;
    private Grade grade;
    private byte[] image;

    public static HotelDto hotelToDto(Hotel hotel){
        HotelDto hotelDto = new HotelDto();
        hotelDto.hotel_id = hotel.getHotel_id();
        hotelDto.name = hotel.getName();
        hotelDto.street = hotel.getStreet();
        hotelDto.city = hotel.getCity();
        hotelDto.state = hotel.getState();
        hotelDto.country = hotel.getCountry();
        hotelDto.zipcode = hotel.getZipcode();
        hotelDto.phoneNumber = hotel.getPhoneNumber();
        hotelDto.email = hotel.getEmail();
        hotelDto.grade = hotel.getGrade();
        return hotelDto;
    }

    public void addImage(byte[] image){
        this.image = image;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public String getName() {
        return name;
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

    public String getZipcode() {
        return zipcode;
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
}
