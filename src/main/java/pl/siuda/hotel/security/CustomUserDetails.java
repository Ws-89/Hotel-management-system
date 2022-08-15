package pl.siuda.hotel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.models.Admin;
import pl.siuda.hotel.models.Guest;

import java.util.Collection;
import java.util.UUID;

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
        return admin != null ? admin.getAuthorities() : guest.getAuthorities();
    }

    @Override
    public String getPassword() {
        return admin != null ? admin.getPassword() : guest.getPassword();
    }

    public UUID getId() {
        return admin != null ? admin.getAdminId() : guest.getGuestId();
    }

    @Override
    public String getUsername() {
        return admin != null ? admin.getUsername() : guest.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return admin != null ? admin.isAccountNonLocked() : guest.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return admin != null ? admin.isEnabled() : guest.isEnabled();
    }
}
