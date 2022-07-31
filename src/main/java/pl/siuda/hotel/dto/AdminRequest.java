package pl.siuda.hotel.dto;

import pl.siuda.hotel.models.Admin;

public class AdminRequest {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;

    public AdminRequest(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void copyDtoToEntity(Admin admin){
        admin.setFirstName(this.firstName);
        admin.setLastName(this.lastName);
        admin.setEmail(this.email);
        admin.setPassword(this.password);
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

    public String getPassword() {
        return password;
    }


}
