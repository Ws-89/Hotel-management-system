package pl.siuda.hotel.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.siuda.hotel.exception.NotFoundException;
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

class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock(name = "adminRepository")
    AdminRepository adminRepository;
    @Mock(name = "customUserDetailsService")
    CustomUserDetailsService customUserDetailsService;
//    @MockBean
//    EmailValidator emailValidator;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @MockBean
//    CustomUserDetailsService customUserDetailsService;





    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }
    // given
    List<Admin> adminList = Arrays.asList(
            new Admin(1L,"Jon", "Doe", "jondoe@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(3L, "Anna", "Adams", "annadams@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(4L, "Kathrine", "Brown", "katherinebrown@gmail.com", "pass123", ApplicationUserRole.ADMIN)
    );

    @Test
    void adminListReturnsCorrectSize() {
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
    void adminListSizeReturnsFalseNumber() {
        // when
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> list = adminService.findAll();
        // then
        assertNotEquals(3, list.size());
    }

    @Test
    void findAdminById() {
        // when
        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "password123", ApplicationUserRole.ADMIN);
        when(adminRepository.findById(2L)).thenReturn(java.util.Optional
                .of(Kevin));
        Optional<Admin> optionalAdmin = Optional.ofNullable(adminService.getAdminById(2L));
        // then
        assertThat(optionalAdmin.isPresent()).isTrue();
        Admin admin = optionalAdmin.get();
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
    void save() {
//        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", bCryptPasswordEncoder.encode("newpassword"), ApplicationUserRole.ADMIN);
//        when()
    }

    @Test
    void updateAdmin() {
//        Admin Kevin = new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", bCryptPasswordEncoder.encode("newpassword"), ApplicationUserRole.ADMIN);
//        AdminRequest adminRequest = new AdminRequest("Kevin", "Smith", "kevinsmith@gmail.com", "newpassword");
//        when(adminRepository.findById(2L)).thenReturn(java.util.Optional
//                .of(Kevin));
//        Optional<Admin> optionalAdmin = Optional.ofNullable(adminService.getAdminById(2L));
//
//        assertThat(optionalAdmin.isPresent()).isTrue();
//        Admin admin = optionalAdmin.get();
//        when(adminRepository.save(admin)).thenReturn(admin);
//
//        Admin updatedAdmin = adminService.updateAdmin(2L, adminRequest)
//        verify(adminRepository).save(admin);
    }

    @Test
    void deleteAdmin() {
    }
}