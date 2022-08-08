package pl.siuda.hotel.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.requests.JwtRequest;
import pl.siuda.hotel.security.jwt.JwtResponse;
import pl.siuda.hotel.services.JwtUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1")
public class JwtController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtUserDetailsService.createToken(jwtRequest);
    }
}
