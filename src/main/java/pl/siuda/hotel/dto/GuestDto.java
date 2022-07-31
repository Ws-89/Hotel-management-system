package pl.siuda.hotel.dto;

import pl.siuda.hotel.models.Guest;

import java.io.Serializable;

public class GuestDto implements Serializable {
    private Long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String phoneNumber;

    public static GuestDto guestToDto (Guest guest){
        GuestDto guestDto = new GuestDto();
        guestDto.guestId = guest.getGuestId();
        guestDto.firstName = guest.getFirstName();
        guestDto.lastName = guest.getLastName();
        guestDto.email = guest.getEmail();
        guestDto.street = guest.getStreet();
        guestDto.city = guest.getCity();
        guestDto.state = guest.getState();
        guestDto.country = guest.getCountry();
        guestDto.zipCode = guest.getZipcode();
        guestDto.phoneNumber = guest.getPhoneNumber();
        return guestDto;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getGuestId() {
        return guestId;
    }

    public void setGuestId(Long guestId) {
        this.guestId = guestId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
