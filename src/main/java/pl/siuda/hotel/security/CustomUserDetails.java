package pl.siuda.hotel.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.guest.Guest;

import java.util.Collection;
import java.util.Optional;

public class CustomUserDetails implements UserDetails {

    private Admin admin;
    private Guest guest;

    public CustomUserDetails(Optional<Admin> admin) {
        this.admin = admin;
    }

    public CustomUserDetails(Optional<Guest> guest) {
        this.guest = guest;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
