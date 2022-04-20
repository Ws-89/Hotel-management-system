package pl.siuda.hotel.registration;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/registration")
    public void register(@RequestBody RegistrationRequest request){
        registrationService.register(request);
    }
}
