package pl.siuda.hotel.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.Set;
import java.util.stream.Collectors;

import static pl.siuda.hotel.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    GUEST(Sets.newHashSet(
            RESERVATION_GUEST
    )),

    EMPLOYER(Sets.newHashSet(
            HOTEL_READ,
            HOTEL_DESCRIPTION_UPDATE,
            ROOM_DESCRIPTION_UPDATE,
            ROOM_READ,
            RESERVATION_READ,
            RESERVATION_WRITE
    )),

    ADMIN(Sets.newHashSet(
            HOTEL_READ,
            HOTEL_DESCRIPTION_UPDATE,
            HOTEL_WRITE,
            ROOM_READ,
            ROOM_DESCRIPTION_UPDATE,
            ROOM_WRITE,
            RESERVATION_READ,
            RESERVATION_WRITE
    ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;

    }
}
