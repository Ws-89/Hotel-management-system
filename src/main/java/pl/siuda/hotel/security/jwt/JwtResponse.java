package pl.siuda.hotel.security.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import pl.siuda.hotel.security.CustomUserDetails;

public class JwtResponse {

    private UserDetails userDetails;
    private String jwtToken;

    public JwtResponse(UserDetails userDetails, String jwtToken) {
        this.userDetails = userDetails;
        this.jwtToken = jwtToken;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(CustomUserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }
}
