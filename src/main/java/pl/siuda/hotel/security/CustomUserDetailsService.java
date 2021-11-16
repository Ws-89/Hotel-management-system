package pl.siuda.hotel.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestRepo;

import java.util.Optional;

public class CustomUserDetailsService implements UserDetailsService {


    private final AdminRepository adminRepository;
    private final GuestRepo guestRepo;

    public CustomUserDetailsService(AdminRepository adminRepository, GuestRepo guestRepo) {
        this.adminRepository = adminRepository;
        this.guestRepo = guestRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepository.findByEmail(email);
        Optional<Guest> guest = guestRepo.findByEmail(email);

        if(admin.isEmpty() && guest.isEmpty()){
            throw new UsernameNotFoundException(String.format("Username %s not found", email));
        }else if(admin.isPresent() && guest.isEmpty()){
            return new CustomUserDetails(admin);
        }else {
            return new CustomUserDetails(guest);
        }
    }
}
