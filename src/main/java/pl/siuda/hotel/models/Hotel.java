package pl.siuda.hotel.models;

import lombok.Builder;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.embeddedClasses.Contact;
import pl.siuda.hotel.models.enums.Grade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_hotel")
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.availableHotels", attributeNodes = {
                @NamedAttributeNode(value = "rooms", subgraph = "subgraph.rooms")
        }, subgraphs = {
                @NamedSubgraph(name = "subgraph.rooms",
                        attributeNodes = {@NamedAttributeNode(value = "reservations")})
        }),
        @NamedEntityGraph(name = "graph.hotelsAndRooms", attributeNodes = {
                @NamedAttributeNode(value = "rooms")
        })})
public class Hotel implements Serializable {

    @Id
    @SequenceGenerator(
            name = "tbl_hotel_sequence", sequenceName = "tbl_hotel_sequence", allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE, generator = "tbl_hotel_sequence"
    )
    @Column(name = "hotel_id")
    private Long hotelId;
    private String name;
    @Embedded
    private Address address;
    @Embedded
    private Contact contact;
    @Enumerated(EnumType.ORDINAL)
    private Grade grade;
    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private Set<Room> rooms = new HashSet<>();
    private String image;
    private Boolean enabled;

    public Hotel() {
    }

    public Hotel(Long hotelId, String name, Address address, Contact contact, Grade grade, Set<Room> rooms, String image, Boolean enabled) {
        this.hotelId = hotelId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.grade = grade;
        this.rooms = rooms;
        this.image = image;
        this.enabled = enabled;
    }

    public void addRoom(Room room) {
        if (rooms == null) {
            this.rooms = new HashSet<>();
        }
        if (!rooms.add(room))
            throw new IllegalArgumentException("Something went wrong. Cannot add room to this hotel");

        room.setHotel(this);
    }

    public void removeRoom(Room room) {
        if (rooms == null) {
            this.rooms = new HashSet<>();
        }
        if (!rooms.remove(room))
            throw new IllegalArgumentException("Something went wrong. Cannot remove room from this hotel");

        room.setHotel(null);
    }

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

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hotel_id=" + hotelId +
                ", name='" + name + '\'' +
                ", address=" + address +
                ", contact=" + contact +
                ", grade=" + grade +
                ", rooms=" + rooms +
                '}';
    }
}
