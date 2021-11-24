package pl.siuda.hotel.guest;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.embeddeClasses.Address;
import pl.siuda.hotel.registration.token.ConfirmationToken;
import pl.siuda.hotel.reservation.Reservation;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.io.Serializable;
import java.util.*;

public class Guest implements UserDetails, Serializable {

    @Id
    @Column("GUEST_ID")
    private Long guest_id;
    @MappedCollection(keyColumn = "GUEST_ID", idColumn = "GUEST_ID")
    private Set<Reservation> reservations = new HashSet<>();
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean locked = false;
    private boolean enabled = false;
    private ApplicationUserRole applicationUserRole;
    @Embedded(onEmpty = Embedded.OnEmpty.USE_NULL)
    private Address address;
    @MappedCollection(keyColumn = "GUEST_ID", idColumn = "GUEST_ID")
    private Set<ConfirmationToken> confirmationTokens = new HashSet<>();

    public Guest() {
    }

    public Guest(String firstName, String lastName, String email, String password, Address address, ApplicationUserRole applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.applicationUserRole = applicationUserRole;
    }


    public Set<ConfirmationToken> getConfirmationTokens() {
        return confirmationTokens;
    }

    public void addConfirmationTokens(ConfirmationToken confirmationTokens) {
        this.confirmationTokens.add(confirmationTokens);
    }

    public void addReservation(Reservation reservation){
        this.reservations.add(reservation);
    }

    public Long getGuest_id() {
        return guest_id;
    }

    public Set<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(Set<Reservation> reservations) {
        this.reservations = reservations;
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



}
