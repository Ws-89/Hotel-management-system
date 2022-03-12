package pl.siuda.hotel.registration;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.embeddedClasses.Address;
import pl.siuda.hotel.guest.Guest;
import pl.siuda.hotel.guest.GuestService;
import pl.siuda.hotel.security.ApplicationUserRole;

import java.time.LocalDateTime;

@Service
public class RegistrationService {

    private final GuestService guestService;
    private final EmailValidator emailValidator;

    public RegistrationService(GuestService guestService, EmailValidator emailValidator) {
        this.guestService = guestService;
        this.emailValidator = emailValidator;
    }

    public Guest register(RegistrationRequest request){

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail){
            throw new IllegalStateException("email not valid");
        }
        return guestService.signUpGuest(
                new Guest(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        new Address(request.getStreet(),
                                request.getCity(),
                                request.getState(),
                                request.getCountry(),
                                request.getZipcode()),
                        request.getPhoneNumber(),
                        ApplicationUserRole.GUEST
                )
        );
    }
}
