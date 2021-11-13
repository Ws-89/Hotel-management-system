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
    RESERVATION_GUEST("reservation:guest");

    private final String permission;


    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }


}
