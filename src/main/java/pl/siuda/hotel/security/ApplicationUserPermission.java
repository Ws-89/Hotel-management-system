package pl.siuda.hotel.security;

public enum ApplicationUserPermission {
    HOTEL_READ("hotel:read"),
    HOTEL_WRITE("hotel:write"),
    HOTEL_DESCRIPTION_UPDATE("hotel:description"),

    ROOM_READ("room:read"),
    ROOM_WRITE("room:write"),
    ROOM_DESCRIPTION_UPDATE("room:description"),

    RESERVATION_READ("reservation:read"),
    RESERVATION_WRITE("reservation:write"),
    RESERVATION_GUEST("reservation:guest"),

    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),

    GUEST_WRITE("guest:write"),
    GUEST_READ("guest:read"),
    GUEST_UPDATE("guest:update"),

    GUEST_PROFILE_READ("guest:profile_read"),
    GUEST_PROFILE_WRITE("guest:profile_write");

    private final String permission;


    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
