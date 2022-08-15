package pl.siuda.hotel.models;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.models.embeddedClasses.Address;

import pl.siuda.hotel.security.ApplicationUserRole;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tbl_guest")
@Builder
@NamedEntityGraphs({
        @NamedEntityGraph(name = "graph.guestProfile", attributeNodes = {
                @NamedAttributeNode(value = "reservations")
        }),
        @NamedEntityGraph(name = "graph.userReservationsRooms",
                attributeNodes = {
                    @NamedAttributeNode(value = "reservations", subgraph = "subgraph.reservations")
                }, subgraphs = {
                    @NamedSubgraph(name = "subgraph.reservations", attributeNodes = {
                        @NamedAttributeNode(value = "room")
                })
        })
})
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "guestId")
public class Guest implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "guest_id")
    private UUID guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "guest", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<Reservation>();
    private boolean locked = false;
    private boolean enabled = true;
    @Enumerated(EnumType.STRING)
    private ApplicationUserRole applicationUserRole;
    @Embedded
    private Address guestAddress;
    private String phoneNumber;

    public Guest(UUID guestId, String firstName, String lastName, String email, String password, List<Reservation> reservations, boolean locked, boolean enabled, ApplicationUserRole applicationUserRole, Address guestAddress, String phoneNumber) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.reservations = reservations;
        this.locked = false;
        this.enabled = true;
        this.applicationUserRole = applicationUserRole;
        this.guestAddress = guestAddress;
        this.phoneNumber = phoneNumber;
    }

    public Guest() {
    }

    public void addReservation(Reservation reservation) {
        if(this.reservations == null)
            this.reservations = new ArrayList<>();

        if(!reservations.add(reservation))
            throw new IllegalArgumentException("System was not able to add reservation to guest");

        reservation.setGuest(this);
    }

    public void removeReservation(Reservation reservation) {
        if(this.reservations == null)
            this.reservations = new ArrayList<>();

        if(!reservations.remove(reservation))
            throw new IllegalArgumentException("System was not able to remove reservation of guest");

        reservation.setGuest(null);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        locked = locked;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }

    public void setApplicationUserRole(ApplicationUserRole applicationUserRole) {
        this.applicationUserRole = applicationUserRole;
    }

    public Address getGuestAddress() {
        return guestAddress;
    }

    public void setGuestAddress(Address guestAddress) {
        this.guestAddress = guestAddress;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return applicationUserRole.getGrantedAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public String getStreet(){
        return this.guestAddress.getStreet();
    }

    public String getCity(){
        return this.guestAddress.getCity();
    }

    public String getState(){
        return this.guestAddress.getState();
    }

    public String getCountry(){
        return this.guestAddress.getCountry();
    }

    public String getZipcode(){
        return this.guestAddress.getZipCode();
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
