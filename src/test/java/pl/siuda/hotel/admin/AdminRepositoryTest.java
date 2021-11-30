package pl.siuda.hotel.admin;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import pl.siuda.hotel.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
class AdminRepositoryTest {

    @Autowired
    AdminRepository adminRepository;

    @Test
    void findAdminById() {
        Admin admin = adminRepository.findById(2L).orElseThrow(()-> new NotFoundException("Not found"));
        assertTrue(admin.)
    }
}