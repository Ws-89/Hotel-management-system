package pl.siuda.hotel.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static pl.siuda.hotel.security.ApplicationUserPermission.*;
import static pl.siuda.hotel.security.ApplicationUserRole.ADMIN;


@Configuration
@EnableWebSecurity(debug = true)
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


        http.csrf().disable()
                .cors()
                .and()
                .authorizeRequests().antMatchers("/", "index", "/authenticate", "/registration/**").permitAll()
                .antMatchers(HttpHeaders.ALLOW).permitAll()
                .antMatchers(HttpMethod.GET, "/admin/management/hotels/**").hasAuthority(HOTEL_READ.getPermission())
                .antMatchers(HttpMethod.GET, "/admin/management/rooms/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.POST, "/admin/management/hotels/**").hasAuthority(HOTEL_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/admin/management/rooms/**").hasRole(ADMIN.name())
//                .antMatchers(HttpMethod.PUT, "/admin/management/hotels/**").hasAuthority(HOTEL_DESCRIPTION_UPDATE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/admin/management/rooms/**").hasRole(ADMIN.name())
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
