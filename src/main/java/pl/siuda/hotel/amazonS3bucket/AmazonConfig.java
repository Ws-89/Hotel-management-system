package pl.siuda.hotel.amazonS3bucket;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {

    @Value("${app.amazon.accesskey}")
    private String amazonAccessKey;
    @Value("${app.amazon.secretkey}")
    private String amazonSecretKey;

    @Bean
    public AmazonS3 S3(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(
                amazonAccessKey,
                amazonSecretKey
        );

        return AmazonS3ClientBuilder
                .standard()
                .withRegion("eu-west-2")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }
}
