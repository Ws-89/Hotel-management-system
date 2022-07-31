package pl.siuda.hotel.controllers;

import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.dto.RegistrationRequest;
import pl.siuda.hotel.services.RegistrationService;

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
