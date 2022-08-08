package pl.siuda.hotel.dto;

import pl.siuda.hotel.models.Admin;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.io.Serializable;

public class AdminDto implements Serializable {

    private Long adminId;
    private String firstName;
    private String lastName;
    private String email;
    private ApplicationUserRole applicationUserRole;

    public static AdminDto adminToDto(Admin admin){
        AdminDto adminDto = new AdminDto();
        adminDto.adminId = admin.getAdminId();
        adminDto.firstName = admin.getFirstName();
        adminDto.lastName = admin.getLastName();
        adminDto.email = admin.getEmail();
        adminDto.applicationUserRole = admin.getApplicationUserRole();
        return adminDto;
    }

    public Long getAdminId() {
        return adminId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }
}
