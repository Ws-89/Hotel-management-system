//package pl.siuda.hotel.security.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import pl.siuda.hotel.security.CustomUserDetailsService;
//import pl.siuda.hotel.security.jwt.JwtAuthenticationEntryPoint;
//import pl.siuda.hotel.security.jwt.JwtRequestFilter;
//
//
//
//import static pl.siuda.hotel.security.ApplicationUserPermission.*;
//import static pl.siuda.hotel.security.ApplicationUserRole.*;
//
//@Configuration
//@EnableWebSecurity(debug = true)
//public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final CustomUserDetailsService customUserDetailsService;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//
//    public ApplicationSecurityConfig(CustomUserDetailsService customUserDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.customUserDetailsService = customUserDetailsService;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//
//    }
//
//    @Value("${spring.security.debug:true}")
//    boolean securityDebug;
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.debug(securityDebug);
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//
//        http
//                .csrf().disable()
//                .cors()
//                .and()
//                .authorizeRequests()
//                .antMatchers("/", "index", "/css/*", "/js/*", "/api/v1/registration/**").permitAll()
////                .antMatchers(HttpMethod.POST, "/management/api/hotels/**").hasAuthority(HOTEL_WRITE.name())
////                .antMatchers(HttpMethod.PUT, "/management/api/hotels/**").hasAuthority(HOTEL_WRITE.name())
////                .antMatchers(HttpMethod.DELETE, "/management/api/hotels").hasAuthority(HOTEL_READ.name())
//                .antMatchers(HttpMethod.GET, "/management/api/hotels").hasAuthority(HOTEL_READ.getPermission())
//                .antMatchers(HttpMethod.GET, "/management/api/hotels").hasRole(ADMIN.name())
////                .antMatchers(HttpMethod.GET, "/management/api/hotels/**").hasAuthority(HOTEL_READ.name())
////                .antMatchers(HttpMethod.POST, "/management/api/rooms/**").hasAuthority(ROOM_WRITE.name())
////                .antMatchers(HttpMethod.PUT, "/management/api/rooms/**").hasAuthority(ROOM_WRITE.name())
////                .antMatchers(HttpMethod.DELETE, "/management/api/rooms/**").hasAuthority(ROOM_WRITE.name())
//                .antMatchers(HttpMethod.GET, "/management/api/rooms").hasAuthority(ROOM_READ.getPermission())
////                .antMatchers(HttpMethod.GET, "/reservations").hasAuthority(RESERVATION_GUEST.getPermission())
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .httpBasic();
//
//
//
//    }
//
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider =
//                new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(bCryptPasswordEncoder);
//        provider.setUserDetailsService(customUserDetailsService);
//        return provider;
//    }
//
//
//
//}
