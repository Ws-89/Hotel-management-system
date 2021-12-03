package pl.siuda.hotel.admin;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.EmailValidator;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    public List<Admin> findAll(){
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Admin getAdminById(Long id){
        return adminRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Admin with id %s not found")));
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

        return adminRepository.save(admin);
    }

    public Admin updateAdmin(Long id, AdminRequest adminRequest){
        Admin admin = adminRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Admin with id %s not found", id)));
        adminRequest.copyDtoToEntity(admin);
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id){
        Admin admin = adminRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Admin with id %s not found")));
        adminRepository.delete(admin);
    }



}
