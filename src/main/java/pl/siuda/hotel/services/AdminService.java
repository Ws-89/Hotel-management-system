package pl.siuda.hotel.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.siuda.hotel.dto.AdminDto;
import pl.siuda.hotel.dto.AdminRequest;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.models.Admin;
import pl.siuda.hotel.util.EmailValidator;
import pl.siuda.hotel.repositories.AdminRepository;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.security.CustomUserDetailsService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AdminRepository adminRepository;
    private final EmailValidator emailValidator;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailsService customUserDetailsService;

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

        Admin admin = Admin.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(encodedPassword)
                .applicationUserRole(ApplicationUserRole.ADMIN).build();

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
