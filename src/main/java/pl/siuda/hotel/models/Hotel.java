package pl.siuda.hotel.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;
import pl.siuda.hotel.roomGroup.RoomGroup;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Hotel implements Serializable {

    @Id
    @SequenceGenerator(
            name = "tbl_hotel_sequence", sequenceName = "tbl_hotel_sequence", allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "tbl_hotel_sequence"
    )
    private Long hotelId;
    private String name;
    private Address address;
    private Contact contact;
    private Grade grade;
    private Set<RoomGroup> roomGroup = new HashSet<>();
    private String image;


    public Long getHotelId() {
        return hotelId;
    }

    public void setHotelId(Long hotelId) {
        this.hotelId = hotelId;
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

    public Set<RoomGroup> getRooms() {
        return roomGroup;
    }

    public void addRoomGroup(RoomGroup roomGroup) {
        this.roomGroup.add(roomGroup);
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
        return address.getZipCode();
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
                "hotel_id=" + hotelId +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", grade=" + grade +
                ", rooms=" + roomGroup +
                '}';
    }
}
