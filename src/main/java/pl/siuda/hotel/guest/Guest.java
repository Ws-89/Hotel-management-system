package pl.siuda.hotel.guest;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.embeddedClasses.Address;

import pl.siuda.hotel.reservation.availabilityCart.AvailabilityCart;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.io.Serializable;
import java.util.*;

public class Guest implements UserDetails, Serializable {

    @Id
    @Column("GUEST_ID")
    private Long guestId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean locked = false;
    private boolean enabled = true;
    private ApplicationUserRole applicationUserRole;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Address address;
    private String phoneNumber;
    @MappedCollection(idColumn = "AVAILABILITY_CART_ID")
    private AvailabilityCart availabilityCart;

    public Guest() {
    }

    public Guest(Long guestId, String firstName, String lastName, String email, String password, Address address, String phoneNumber, ApplicationUserRole applicationUserRole) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.applicationUserRole = applicationUserRole;
    }

    public Guest(String firstName, String lastName, String email, String password, Address address, String phoneNumber, Boolean enabled,  ApplicationUserRole applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.enabled = enabled;
        this.applicationUserRole = applicationUserRole;
    }


    public Guest(String firstName, String lastName, String email, String password, Address address, String phoneNumber, ApplicationUserRole applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.applicationUserRole = applicationUserRole;
    }

    public Guest(Long guestId, String firstName, String lastName, String email, String password, boolean locked, boolean enabled, ApplicationUserRole applicationUserRole, Address address, String phoneNumber, AvailabilityCart availabilityCart) {
        this.guestId = guestId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.locked = locked;
        this.enabled = enabled;
        this.applicationUserRole = applicationUserRole;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.availabilityCart = availabilityCart;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
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
        return this.address.getStreet();
    }

    public String getCity(){
        return this.address.getCity();
    }

    public String getState(){
        return this.address.getState();
    }

    public String getCountry(){
        return this.address.getCountry();
    }

    public String getZipcode(){
        return this.address.getZipcode();
    }

    public AvailabilityCart getAvailabilityCart() {
        return availabilityCart;
    }

    public void setAvailabilityCart(AvailabilityCart availabilityCart) {
        this.availabilityCart = availabilityCart;
    }
}
