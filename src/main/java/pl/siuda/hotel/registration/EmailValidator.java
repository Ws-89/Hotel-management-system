package pl.siuda.hotel.registration;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.employee.EmployeeRepository;
import pl.siuda.hotel.guest.GuestRepo;

import java.util.function.Predicate;

@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String email) {
        return true;
    }
}
