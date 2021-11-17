package pl.siuda.hotel.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private final AdminRepository adminRepository;
    private final GuestRepo guestRepo;

    public CustomUserDetailsService(AdminRepository adminRepository, GuestRepo guestRepo) {
        this.adminRepository = adminRepository;
        this.guestRepo = guestRepo;
    }

    public boolean userNotExists(String email){
        Admin admin = adminRepository.findByEmail(email);
        Guest guest = guestRepo.findByEmail(email);
        if(admin == null && guest == null){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email);
        Guest guest = guestRepo.findByEmail(email);

        if(admin == null && guest == null){
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }else if(admin != null && guest == null){
            return new CustomUserDetails(admin);
        }else {
            return new CustomUserDetails(guest);
        }
    }
}
