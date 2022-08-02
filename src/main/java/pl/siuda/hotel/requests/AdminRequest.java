package pl.siuda.hotel.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.siuda.hotel.models.Admin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdminRequest {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

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
