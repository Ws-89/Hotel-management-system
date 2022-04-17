package pl.siuda.hotel.admin;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.io.Serializable;
import java.util.Collection;

public class Admin implements UserDetails, Serializable {

    @Id
    private Long adminId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean locked = false;
    private boolean enabled = true;
    private ApplicationUserRole applicationUserRole;

    public Admin() {
    }

    public Admin(Long adminId, String firstName, String lastName, String email, String password, ApplicationUserRole applicationUserRole) {
        this.adminId = adminId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.applicationUserRole = applicationUserRole;
    }

    public Admin(String firstName, String lastName, String email, String password, ApplicationUserRole applicationUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.applicationUserRole = applicationUserRole;
    }

    public ApplicationUserRole getApplicationUserRole() {
        return applicationUserRole;
    }

    public void setApplicationUserRole(ApplicationUserRole applicationUserRole) {
        this.applicationUserRole = applicationUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return applicationUserRole.getGrantedAuthorities();
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

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + adminId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", enabled=" + enabled +
                ", applicationUserRole=" + applicationUserRole +
                '}';
    }
}
