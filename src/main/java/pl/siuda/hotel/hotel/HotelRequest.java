package pl.siuda.hotel.hotel;

import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.embeddedClasses.Contact;
import pl.siuda.hotel.enums.Grade;

public class HotelRequest {
    private String hotel_name;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String phoneNumber;
    private String email;
    private Grade grade;

    public HotelRequest() {
    }

    public HotelRequest(String hotel_name, String street, String city, String state, String country, String zipcode, String phoneNumber, String email, Grade grade) {
        this.hotel_name = hotel_name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.grade = grade;
    }

    public void copyRequestToEntity(Hotel hotel){
        hotel.setName(hotel_name);
        Address address = new Address();
        address.setStreet(street);
        address.setCity(city);
        address.setState(state);
        address.setCountry(country);
        address.setZipcode(zipcode);
        hotel.setAddress(address);
        Contact contact = new Contact();
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);
        hotel.setContact(contact);
        hotel.setGrade(grade);
    }

    public String getHotel_name() {
        return hotel_name;
    }

    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
