//package pl.siuda.hotel.configuration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Profile;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//
//import javax.sql.DataSource;
//
//@Profile("test")
//@Configuration
//public class h2embeddedDatabase {

//    @Bean
//    DataSource dataSource(){
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("org.h2.Driver");
//        driverManagerDataSource.setUrl("jdbc:h2:mem:db");
//        driverManagerDataSource.setUsername("sa");
//        driverManagerDataSource.setPassword("");
//        return driverManagerDataSource;
//    }
//}
