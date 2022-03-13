package pl.siuda.hotel.hotel;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.embeddedClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.room.Room;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Hotel implements Serializable {

    @Id
    private Long hotel_id;
    private String name;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Address address;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Contact contact;
    private Grade grade;
    @MappedCollection(idColumn = "HOTEL_ID")
    private Set<Room> rooms = new HashSet<>();
    private String image;

    public Hotel() {
    }

    public Hotel(Long hotel_id, String name, Address address, Contact contact, Grade grade) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.grade = grade;
    }

    public Hotel(String name, Address address, Contact contact, Grade grade) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.grade = grade;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
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

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    public String getStreet(){
        return address.getStreet();
    }

    public String getCity(){
        return address.getCity();
    }

    public String getState(){
        return address.getState();
    }

    public String getCountry(){
        return address.getCountry();
    }

    public String getZipcode(){
        return address.getZipcode();
    }

    public String getPhoneNumber(){
        return contact.getPhoneNumber();
    }

    public String getEmail(){
        return contact.getEmail();
    }

    public String getImageUrl() {
        return image;
    }

    public void setImageUrl(String imageUrl) {
        this.image = imageUrl;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotel_id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", grade=" + grade +
                ", rooms=" + rooms +
                '}';
    }
}
