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

    public List<AdminDto> findAllAdmins(){
        return StreamSupport.stream(adminRepository.findAll().spliterator(), false)
                .map(admin -> AdminDto.adminToDto(admin))
                .collect(Collectors.toList());
    }

    public AdminDto getAdminById(Long id){
        return adminRepository.findById(id)
                .map(admin -> AdminDto.adminToDto(admin))
                .orElseThrow(()-> new NotFoundException(String.format("Admin with id %s not found", id)));
    }

    public void saveAdmin(AdminRequest request){
        boolean userNotExists = customUserDetailsService.userNotExists(request.getEmail());
        if(!userNotExists){
            throw new IllegalStateException("email already in use");
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
    }

    public void updateAdmin(Long id, AdminRequest adminRequest){
            Admin admin = adminRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(String.format("Admin with id %s not found", id)));
            adminRequest.copyDtoToEntity(admin);
            adminRepository.save(admin);
    }

    public void deleteAdmin(Long id){
        Admin admin = adminRepository.findById(id).orElseThrow(()-> new NotFoundException(String.format("Admin with id %s not found")));
        adminRepository.delete(admin);
    }



}
