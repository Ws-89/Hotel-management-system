package pl.siuda.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.siuda.hotel.admin.Admin;
import pl.siuda.hotel.admin.AdminRepository;
import pl.siuda.hotel.security.ApplicationUserRole;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableJdbcRepositories
public class HotelApplication {

	@Autowired
	AdminRepository adminRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);


	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token",
				"Authorization", "Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);

	}

	@PostConstruct
	private void init() {
		buildSuperAdmin();
	}

	private void buildSuperAdmin() {

		boolean adminExists = adminRepository.findByEmail("wiktorsiuda@gmail.com").isPresent();
		if (!adminExists) {
			Admin admin = new Admin(
					"Wiktor",
					"Siuda",
					"wiktorsiuda@gmail.com",
					"password123",
					ApplicationUserRole.ADMIN
			);
			adminRepository.save(admin);
		}


	}
}

