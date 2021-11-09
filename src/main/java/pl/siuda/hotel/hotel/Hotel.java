package pl.siuda.hotel.hotel;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.room.Room;

import java.util.HashSet;
import java.util.Set;

public class Hotel {

    @Id
    private Long id;
    private String name;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Address address;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Contact contact;
    private Grade grade;
    @MappedCollection(idColumn = "HOTEL_ID")
    private Set<OpeningTimes> openingTimes = new HashSet<>();
    @MappedCollection(idColumn = "HOTEL_ID")
    private Set<Room> rooms = new HashSet<>();

    public Hotel() {
    }

    public void updateDetails(Hotel hotel){
        this.name = hotel.name;
        this.address = hotel.address;
        this.contact = hotel.contact;
        this.grade = hotel.grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }
    public String getStreet() {
        return address.getStreet();
    }

    public void setStreet(String street) {
        address.setStreet(street);
    }

    public String getState() {
        return address.getState();
    }

    public void setState(String state) {
        address.setState(state);
    }

    public String getCity() {
        return address.getCity();
    }

    public void setCity(String city) {
        address.setCity(city);
    }

    public String getCountry() {
        return address.getCountry();
    }

    public void setCountry(String country) {
        address.setCountry(country);
    }

    public String getZipcode() {
        return address.getZipcode();
    }

    public void setZipcode(String zipcode) {
        address.setZipcode(zipcode);
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getPhoneNumber(){
        return contact.getPhoneNumber();
    }

    public String getEmail(){
        return contact.getEmail();
    }

    public void setPhoneNumber(String phoneNumber){
        this.contact.setPhoneNumber(phoneNumber);
    }

    public void setEmail(String email){
        this.contact.setEmail(email);
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<OpeningTimes> getOpeningTimes() {
        return openingTimes;
    }

    public void addOpeningTimes(OpeningTimes openingTime) {
        this.openingTimes.add(openingTime);
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", grade=" + grade +
                ", openingTimes=" + openingTimes +
                ", rooms=" + rooms +
                '}';
    }

}
