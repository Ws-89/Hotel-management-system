package pl.siuda.hotel.admin;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.EmailValidator;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.security.CustomUserDetailsService;

@Service
public class AdminService {

    private final AdminRepository adminRepository;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

    public AdminService(AdminRepository adminRepository, EmailValidator emailValidator, BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService customUserDetailsService) {
        this.adminRepository = adminRepository;
        this.emailValidator = emailValidator;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.customUserDetailsService = customUserDetailsService;
    }

    public Admin save(AdminRequest request){
        boolean userNotExists = customUserDetailsService.userNotExists(request.getEmail());
        if(!userNotExists){
            throw new NotFoundException("email already in use");
        }
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

}
