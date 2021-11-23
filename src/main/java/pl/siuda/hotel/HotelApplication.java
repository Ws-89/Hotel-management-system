package pl.siuda.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import pl.siuda.hotel.admin.AdminRequest;
import pl.siuda.hotel.admin.AdminService;
import pl.siuda.hotel.security.CustomUserDetailsService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableJdbcRepositories
@EnableSwagger2
public class HotelApplication {

	@Autowired
	CustomUserDetailsService customUserDetailsService;

	@Autowired
	AdminService adminService;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);


	}

	@PostConstruct
	private void init() {
		buildSuperAdmin();
	}

	private void buildSuperAdmin() {

		boolean adminExists = customUserDetailsService.userNotExists("ws@gmail.com");
		if (adminExists) {
			AdminRequest admin = new AdminRequest(
					"w",
					"s",
					"ws@gmail.com",
					"123"
			);
			adminService.save(admin);
		}


	}
}

