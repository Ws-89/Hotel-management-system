package pl.siuda.hotel.hotel;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.embeddeClasses.Contact;
import pl.siuda.hotel.enums.Grade;
import pl.siuda.hotel.room.Room;

import java.util.HashSet;
import java.util.Set;

public class Hotel {

    @Id
    private Long hotel_id;
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
                "hotel_id=" + hotel_id +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", grade=" + grade +
                ", openingTimes=" + openingTimes +
                ", rooms=" + rooms +
                '}';
    }
}
