package pl.siuda.hotel.imageService;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    @Value("${app.cloud.name}")
    private String appCloudName;
    @Value("${app.api.key}")
    private String appApiKey;
    @Value("${app.secret.key}")
    private String appSecretKey;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", appCloudName,
                "api_key", appApiKey,
                "api_secret", appSecretKey,
                "secure", true));
    }
}
