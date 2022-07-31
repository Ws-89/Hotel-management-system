package pl.siuda.hotel.dto;

import pl.siuda.hotel.models.Hotel;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;

public class HotelRequest {
    private String hotelName;
    private String hotelStreet;
    private String hotelCity;
    private String hotelState;
    private String hotelCountry;
    private String hotelZipCode;
    private String hotelPhone;
    private String hotelEmail;
    private Grade hotelGrade;

    public HotelRequest() {
    }

    public HotelRequest(String hotelName, String hotelStreet, String hotelCity, String hotelState, String hotelCountry, String hotelZipCode, String hotelPhone, String hotelEmail, Grade hotelGrade) {
        this.hotelName = hotelName;
        this.hotelStreet = hotelStreet;
        this.hotelCity = hotelCity;
        this.hotelState = hotelState;
        this.hotelCountry = hotelCountry;
        this.hotelZipCode = hotelZipCode;
        this.hotelPhone = hotelPhone;
        this.hotelEmail = hotelEmail;
        this.hotelGrade = hotelGrade;
    }

    public void copyRequestToEntity(Hotel hotel){
        hotel.setName(hotelName);
        Address address = new Address();
        address.setStreet(hotelStreet);
        address.setCity(hotelCity);
        address.setState(hotelState);
        address.setCountry(hotelCountry);
        address.setZipCode(hotelZipCode);
        hotel.setAddress(address);
        Contact contact = new Contact();
        contact.setPhoneNumber(hotelPhone);
        contact.setEmail(hotelEmail);
        hotel.setContact(contact);
        hotel.setGrade(hotelGrade);
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelStreet() {
        return hotelStreet;
    }

    public void setHotelStreet(String hotelStreet) {
        this.hotelStreet = hotelStreet;
    }

    public String getHotelCity() {
        return hotelCity;
    }

    public void setHotelCity(String hotelCity) {
        this.hotelCity = hotelCity;
    }

    public String getHotelState() {
        return hotelState;
    }

    public void setHotelState(String hotelState) {
        this.hotelState = hotelState;
    }

    public String getHotelCountry() {
        return hotelCountry;
    }

    public void setHotelCountry(String hotelCountry) {
        this.hotelCountry = hotelCountry;
    }

    public String getHotelZipCode() {
        return hotelZipCode;
    }

    public void setHotelZipCode(String hotelZipCode) {
        this.hotelZipCode = hotelZipCode;
    }

    public String getHotelPhone() {
        return hotelPhone;
    }

    public void setHotelPhone(String hotelPhone) {
        this.hotelPhone = hotelPhone;
    }

    public String getHotelEmail() {
        return hotelEmail;
    }

    public void setHotelEmail(String hotelEmail) {
        this.hotelEmail = hotelEmail;
    }

    public Grade getHotelGrade() {
        return hotelGrade;
    }

    public void setHotelGrade(Grade hotelGrade) {
        this.hotelGrade = hotelGrade;
    }
}
