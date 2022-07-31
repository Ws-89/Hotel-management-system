package pl.siuda.hotel.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.models.Admin;
import pl.siuda.hotel.repositories.AdminRepository;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.repositories.GuestRepository;
import pl.siuda.hotel.security.CustomUserDetails;
import pl.siuda.hotel.util.JwtUtil;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final GuestRepository guestRepository;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    public JwtUserDetailsService(AdminRepository adminRepository, GuestRepository guestRepository) {
        this.adminRepository = adminRepository;
        this.guestRepository = guestRepository;
    }

    public void authenticate(String userName, String userPassword) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, userPassword));
        }catch(DisabledException e){
            throw new Exception("User is disabled");
        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials from user");
        }
    }

    public JwtResponse createToken(JwtRequest jwtRequest) throws Exception{
        String userName = jwtRequest.getUserName();
        String userPassword = jwtRequest.getUserPassword();
        authenticate(userName, userPassword);
        final UserDetails userDetails = loadUserByUsername(userName);

        String generatedToken = jwtUtil.generateToken(userDetails);

        return new JwtResponse(userDetails, generatedToken);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        Guest guest = guestRepository.findByEmail(email);

        if(admin == null && guest == null){
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }else if(admin != null && guest == null){
            return new CustomUserDetails(admin);
        }else {
            return new CustomUserDetails(guest);
        }
    }
}
