package pl.siuda.hotel.admin;

import pl.siuda.hotel.security.ApplicationUserRole;

import java.io.Serializable;

public class AdminDto implements Serializable {

    private Long admin_id;
    private String firstName;
    private String lastName;
    private String email;
    private ApplicationUserRole applicationUserRole;

    public static AdminDto adminToDto(Admin admin){
        AdminDto adminDto = new AdminDto();
        adminDto.admin_id = admin.getAdmin_id();
        adminDto.firstName = admin.getFirstName();
        adminDto.lastName = admin.getLastName();
        adminDto.email = admin.getEmail();
        adminDto.applicationUserRole = admin.getApplicationUserRole();
        return adminDto;
    }

    public Long getAdmin_id() {
        return admin_id;
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
