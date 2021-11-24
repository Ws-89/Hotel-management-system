package pl.siuda.hotel.guest;

import java.io.Serializable;

public class GuestDto implements Serializable {
    private Long guest_id;
    private String firstName;
    private String lastName;
    private String email;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;

    public static GuestDto guestToDto (Guest guest){
        GuestDto guestDto = new GuestDto();
        guestDto.guest_id = guest.getGuest_id();
        guestDto.firstName = guest.getFirstName();
        guestDto.lastName = guest.getLastName();
        guestDto.email = guest.getEmail();
        guestDto.street = guest.getStreet();
        guestDto.city = guest.getCity();
        guestDto.state = guest.getState();
        guestDto.country = guest.getCountry();
        guestDto.zipcode = guest.getZipcode();
        return guestDto;
    }

    public Long getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(Long guest_id) {
        this.guest_id = guest_id;
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

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
