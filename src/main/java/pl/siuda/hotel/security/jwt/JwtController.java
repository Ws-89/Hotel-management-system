package pl.siuda.hotel.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.siuda.hotel.security.CustomUserDetailsService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping
public class JwtController {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception{
        return jwtUserDetailsService.createToken(jwtRequest);
    }
}
