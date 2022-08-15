package pl.siuda.hotel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.siuda.hotel.security.jwt.JwtAuthenticationEntryPoint;
import pl.siuda.hotel.security.jwt.JwtRequestFilter;

import static pl.siuda.hotel.security.ApplicationUserPermission.*;


@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration  extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .requiresChannel()
                .requestMatchers(r -> r.getHeader("X-Forwarded-Proto") != null)
                .requiresSecure()
                .and()
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests().antMatchers("/", "/stripe/events", "/api/v1/authenticate", "/api/v1/registration", "/api/v1/reservations/available-rooms", "/api/v1/reservations/place-a-booking", "/api/v1/user-management/users/**", "/images/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/reservations/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/v1/reservations/**").permitAll()
                .antMatchers(HttpMethod.DELETE, "/api/v1/reservations/**").permitAll()
                .antMatchers(HttpMethod.PUT, "/api/v1/reservations/**").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/hotel-management/hotels/**").hasAuthority(HOTEL_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/hotel-management/hotels/**").hasAuthority(HOTEL_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/v1/hotel-management/hotels/**").hasAuthority(HOTEL_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/hotel-management/hotels/**").hasAuthority(HOTEL_DESCRIPTION_UPDATE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/hotel-management/rooms/**").hasAuthority(ROOM_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/hotel-management/rooms/**").hasAuthority(ROOM_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "//api/v1/hotel-management/rooms/**").hasAuthority(ROOM_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/hotel-management/rooms/**").hasAuthority(ROOM_DESCRIPTION_UPDATE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/admin-management/admins/**").hasAuthority(ADMIN_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/admin-management/admins/**").hasAuthority(ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/v1/admin-management/admins/**").hasAuthority(ADMIN_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/admin-management/admins/**").hasAuthority(ADMIN_UPDATE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/user-management/users/**").hasAuthority(GUEST_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/user-management/users/**").hasAuthority(GUEST_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/user-management/users/**").hasAuthority(GUEST_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/v1/user-management/users/**").hasAuthority(GUEST_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/reservations-management/current-user-reservations").hasAuthority(RESERVATION_GUEST.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/reservations-management/**").hasAuthority(RESERVATION_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/reservations-management/**").hasAuthority(RESERVATION_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/api/v1/reservations-management/**").hasAuthority(RESERVATION_WRITE.getPermission())
                .antMatchers(HttpMethod.DELETE, "/api/v1/reservations-management/**").hasAuthority(RESERVATION_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/api/v1/hotel-management/rooms/get-principal").hasAuthority(GUEST_PROFILE_READ.getPermission())
                .antMatchers(HttpMethod.POST, "/api/v1/reservations/place-a-booking-logged-in").hasAuthority(RESERVATION_GUEST.getPermission())
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);



        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
}
