package pl.siuda.hotel.services;

import org.springframework.stereotype.Service;
import pl.siuda.hotel.requests.RegistrationRequest;
import pl.siuda.hotel.models.embeddedClasses.Address;
import pl.siuda.hotel.models.Guest;
import pl.siuda.hotel.security.ApplicationUserRole;
import pl.siuda.hotel.util.EmailValidator;

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
                Guest.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(request.getPassword())
                        .enabled(true)
                        .locked(false)
                        .guestAddress(Address.builder().street(request.getStreet())
                                        .city(request.getCity())
                                        .state(request.getState())
                                        .country(request.getCountry())
                                .zipCode(request.getZipCode()).build())
                        .phoneNumber(request.getPhoneNumber())
                        .applicationUserRole(ApplicationUserRole.GUEST).build()
                );

    }
}
