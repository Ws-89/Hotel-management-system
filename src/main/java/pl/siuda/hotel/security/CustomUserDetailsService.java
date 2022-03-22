package pl.siuda.hotel.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final GuestRepository guestRepository;


    public CustomUserDetailsService(AdminRepository adminRepository, GuestRepository guestRepository) {
        this.adminRepository = adminRepository;
        this.guestRepository = guestRepository;
    }

    public boolean userNotExists(String email){
        Admin admin = adminRepository.findByEmail(email);
        Guest guest = guestRepository.findByEmail(email);
        return (admin == null && guest == null) ? true : false;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        Guest guest = guestRepository.findByEmail(email);
        if(admin == null && guest == null)
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        return (admin != null && guest == null) ? new CustomUserDetails(admin) : new CustomUserDetails(guest);
    }
}
