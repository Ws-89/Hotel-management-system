package pl.siuda.hotel.admin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

class AdminServiceTest {

    @InjectMocks
    AdminService adminService;

    @Mock(name = "adminRepository")
    AdminRepository adminRepository;
//    @MockBean
//    EmailValidator emailValidator;
//    @MockBean
//    BCryptPasswordEncoder bCryptPasswordEncoder;
//    @MockBean
//    CustomUserDetailsService customUserDetailsService;





    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.openMocks(this);
    }

    List<Admin> adminList = Arrays.asList(
            new Admin(1L,"Jon", "Doe", "jondoe@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(3L, "Anna", "Adams", "annadams@gmail.com", "pass123", ApplicationUserRole.ADMIN),
            new Admin(4L, "Kathrine", "Brown", "katherinebrown@gmail.com", "pass123", ApplicationUserRole.ADMIN)
    );

    @Test
    void adminListReturnsCorrectSize() {
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> list = adminService.findAll();
        assertThat(list.size()).isEqualTo(4);
    }

    @Test
    void adminListReturnsZero() {
        when(adminRepository.findAll()).thenReturn(Collections.emptyList());
        List<Admin> list = adminService.findAll();
        assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void adminListSizeReturnsFalseNumber() {
        when(adminRepository.findAll()).thenReturn(adminList);
        List<Admin> list = adminService.findAll();
        assertNotEquals(3, list.size());
    }

    @Test
    void findAdminById() {
        when(adminRepository.findById(2L)).thenReturn(java.util.Optional.of(new Admin(2L, "Kevin", "Smith", "kevinsmith@gmail.com", "pass123", ApplicationUserRole.ADMIN)));
        

    }

    @Test
    void save() {
    }

    @Test
    void updateAdmin() {
    }

    @Test
    void deleteAdmin() {
    }
}