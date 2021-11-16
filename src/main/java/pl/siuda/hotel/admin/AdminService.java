package pl.siuda.hotel.admin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.registration.EmailValidator;
import pl.siuda.hotel.security.ApplicationUserRole;

@Service
public class AdminService implements UserDetailsService {

    private final AdminRepository adminRepository;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AdminService(AdminRepository adminRepository, EmailValidator emailValidator, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.adminRepository = adminRepository;
        this.emailValidator = emailValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Admin save(AdminRequest request){
        boolean isValid = emailValidator.test(request.getEmail());
        if(!isValid){
            throw new IllegalStateException("email not valid");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());

        Admin admin = new Admin(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                encodedPassword,
                ApplicationUserRole.ADMIN
        );

        adminRepository.save(admin);
        return admin;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return adminRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException(String.format("Username %s not found", email)));
    }
}
