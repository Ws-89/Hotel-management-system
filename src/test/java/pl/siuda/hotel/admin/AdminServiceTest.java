package pl.siuda.hotel.admin;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.siuda.hotel.exception.NotFoundException;
import pl.siuda.hotel.registration.EmailValidator;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.security.CustomUserDetailsService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock(name = "adminRepository")
    AdminRepository adminRepository;
    @Mock(name = "customUserDetailsService")
    CustomUserDetailsService customUserDetailsService;
    @Mock(name = "emailValidator")
    EmailValidator emailValidator;
    @Mock(name = "bCryptPasswordEncoder")
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(adminService).isNotNull();
        assertThat(adminRepository).isNotNull();
        assertThat(customUserDetailsService).isNotNull();
        assertThat(emailValidator).isNotNull();
        assertThat(bCryptPasswordEncoder).isNotNull();
    }

    @Test
    void adminListReturnsCorrectSize() {
        // given
        List<Admin> adminList = Arrays.asList(
                new Admin("Jon", "Doe", "jondoe@gmail.com", "pass123", ApplicationUserRole.ADMIN),
                new Admin( "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN),
                new Admin( "Anna", "Adams", "annadams@gmail.com", "pass123", ApplicationUserRole.ADMIN),
                new Admin("Kathrine", "Brown", "katherinebrown@gmail.com", "pass123", ApplicationUserRole.ADMIN)
        );

        // when
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> list = adminService.findAll();

        // then
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void adminListReturnsZero() {
        // when
        when(adminRepository.findAll()).thenReturn(Collections.emptyList());
        List<Admin> list = adminService.findAll();

        // then
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void findAdminById() {
        // given
        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "password123", ApplicationUserRole.ADMIN);
        // when
        when(adminRepository.findById(2L)).thenReturn(java.util.Optional
                .of(Kevin));
       Admin admin = adminService.getAdminById(2L);
        // then

        assertThat(admin.getAdmin_id()).isEqualTo(2L);
        assertThat(admin.getFirstName()).isEqualTo("Kevin");
        assertThat(admin.getLastName()).isEqualTo("Smith");
        assertThat(admin.getEmail()).isEqualTo("kevinsmith@gmail.com");
        assertThat(admin.getPassword()).isEqualTo("password123");
        assertThat(admin.getApplicationUserRole()).isEqualTo(ApplicationUserRole.ADMIN);
    }

    @Test
    void findAdminByIdNotFound() {
        // when
        when(adminRepository.findById(2L)).thenThrow(new NotFoundException("Admin with id 2 not found"));

        // then
        assertThrows(NotFoundException.class, () -> Optional.ofNullable(adminService.getAdminById(2L)));
    }

    @Test
    void saveAdmin() {
        // given
        AdminRequest Kevin = new AdminRequest("Kevin", "Smith", "kevinsmith@gmail.com", "pass123");
        // when
        when(customUserDetailsService.userNotExists("kevinsmith@gmail.com")).thenReturn(true);
        when(emailValidator.test("kevinsmith@gmail.com")).thenReturn(true);
        when(bCryptPasswordEncoder.encode("pass123")).thenReturn("1");
        adminService.save(Kevin);

        // then
        ArgumentCaptor<Admin> adminArgumentCaptor = ArgumentCaptor.forClass(Admin.class);
        verify(adminRepository).save(adminArgumentCaptor.capture());

        Admin argumentCaptorValue = adminArgumentCaptor.getValue();
        assertThat(argumentCaptorValue.getFirstName()).isEqualTo(Kevin.getFirstName());
        assertThat(argumentCaptorValue.getLastName()).isEqualTo(Kevin.getLastName());
        assertThat(argumentCaptorValue.getEmail()).isEqualTo(Kevin.getEmail());
        assertThat(argumentCaptorValue.getPassword()).isEqualTo("1");
    }

    @Test
    void saveAdminThrowsExceptionEmailAlreadyExists() {
        // given
        AdminRequest Kevin = new AdminRequest("Kevin", "Smith", "kevinsmith@gmail.com", "pass123");
        // when
        when(customUserDetailsService.userNotExists("kevinsmith@gmail.com")).thenReturn(false);

        // then
        assertThrows(IllegalStateException.class, () -> adminService.save(Kevin));
    }

    @Test
    void saveAdminThrowsExceptionEmailIsNotValid() {
        // given
        AdminRequest Kevin = new AdminRequest("Kevin", "Smith", "kevinsmith@gmail.com", "pass123");
        // when
        when(customUserDetailsService.userNotExists("kevinsmith@gmail.com")).thenReturn(true);
        when(emailValidator.test("kevinsmith@gmail.com")).thenReturn(false);
        ;

        // then
        assertThrows(IllegalStateException.class, () -> adminService.save(Kevin));
    }

    @Test
    void updateAdmin() {
        // given
        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN);
        Admin updatedKevin = new Admin(2L, "updatedKevin", "updatedSmith", "updatedkevinsmith@gmail.com", "pass1234", ApplicationUserRole.ADMIN);
        AdminRequest adminRequest = new AdminRequest("updatedKevin", "updatedSmith", "updatedkevinsmith@gmail.com", "pass1234");
        // when
        when(adminRepository.findById(2L)).thenReturn(Optional.of(Kevin));
        adminService.updateAdmin(2L, adminRequest);

        // then
        ArgumentCaptor<Admin> adminArgumentCaptor = ArgumentCaptor.forClass(Admin.class);
        verify(adminRepository).save(adminArgumentCaptor.capture());

        Admin capturedAdmin = adminArgumentCaptor.getValue();
        assertThat(capturedAdmin.getFirstName()).isEqualTo(updatedKevin.getFirstName());
        assertThat(capturedAdmin.getLastName()).isEqualTo(updatedKevin.getLastName());
        assertThat(capturedAdmin.getEmail()).isEqualTo(updatedKevin.getEmail());
        assertThat(capturedAdmin.getPassword()).isEqualTo(updatedKevin.getPassword());
    }

    @Test
    void deleteAdmin() {
        // given
        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN);

        // when
        when(adminRepository.findById(2L)).thenReturn(Optional.of(Kevin));
        adminService.deleteAdmin(2L);
        // then

        ArgumentCaptor<Admin> adminArgumentCaptor = ArgumentCaptor.forClass(Admin.class);
        verify(adminRepository).delete(adminArgumentCaptor.capture());

        Admin adminArgumentCaptorValue = adminArgumentCaptor.getValue();
        assertThat(adminArgumentCaptorValue.getFirstName()).isEqualTo(Kevin.getFirstName());
        assertThat(adminArgumentCaptorValue.getLastName()).isEqualTo(Kevin.getLastName());
        assertThat(adminArgumentCaptorValue.getEmail()).isEqualTo(Kevin.getEmail());
        assertThat(adminArgumentCaptorValue.getPassword()).isEqualTo(Kevin.getPassword());
    }
}