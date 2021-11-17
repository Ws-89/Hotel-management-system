package pl.siuda.hotel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.guest.Guest;

import java.util.Collection;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private Admin admin;
    private Guest guest;

    public CustomUserDetails(Admin admin) {
        this.admin = admin;
    }

    public CustomUserDetails(Guest guest) {
        this.guest = guest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(admin != null & guest == null){
            return admin.getAuthorities();
        } else {
            return guest.getAuthorities();
        }

    }

    @Override
    public String getPassword() {
        if(admin != null & guest == null){
            return admin.getPassword();
        } else {
            return guest.getPassword();
        }
    }

    @Override
    public String getUsername() {
        if(admin != null & guest == null){
            return admin.getUsername();
        } else {
            return guest.getUsername();
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(admin != null & guest == null){
            return admin.isAccountNonLocked();
        } else {
            return guest.isAccountNonLocked();
        }
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(admin != null & guest == null){
            return admin.isEnabled();
        } else {
            return guest.isEnabled();
        }
    }
}
