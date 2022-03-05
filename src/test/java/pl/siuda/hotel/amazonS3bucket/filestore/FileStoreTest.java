package pl.siuda.hotel.amazonS3bucket.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.siuda.hotel.amazonS3bucket.AmazonConfig;


import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.amazonaws.auth.policy.actions.S3Actions.PutObject;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class FileStoreTest {

    @Autowired
    AmazonConfig AmazonConfig;

    @Autowired
    AmazonS3 s3;

    @Autowired
    FileStore fileStore;

    @Test
    void isFileEmptyPassesTest() {
        // given
        String fileName = "image";
        Path path = Paths.get("D:\\Projekty java\\hotel\\hotel\\src\\test\\resources\\hotel-1.jpg");
        byte[] content = new byte[0];
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // when
        MultipartFile result = new MockMultipartFile(fileName, content);
        // then
        fileStore.isFileEmpty(result);
    }

    @Test
    void isFileEmptyThrowsIOException() {
        // given
        String fileName = "image";
        Path path = Paths.get("");

        byte[] content = new byte[0];
        try {
            content = Files.readAllBytes(path);
        } catch (IOException e) {

        }
        MultipartFile result = new MockMultipartFile(fileName, content);
        // then
        assertThrows(IllegalStateException.class, () -> fileStore.isFileEmpty(result));
    }


    @Test
    void isAnImage() {
        // given
        String fileName = "image";
        Path path = Paths.get("D:\\Projekty java\\hotel\\hotel\\src\\test\\resources\\hotel-1.jpg");
        String contentType = "image/jpeg";
        byte[] content = new byte[0];
        try{
            content = Files.readAllBytes(path);
        } catch (IOException e) {

        }
        MultipartFile multipart = new MockMultipartFile(fileName, fileName, contentType, content);
        // then
        fileStore.isAnImage(multipart);
    }

    @Test
    void isNotAnImage() {
        // given
        String fileName = "image";
        Path path = Paths.get("");
        String contentType = "";
        byte[] content = new byte[0];
        try{
            content = Files.readAllBytes(path);
        } catch (IOException e) {

        }
        MultipartFile multipart = new MockMultipartFile(fileName, fileName, contentType, content);
        // then
        assertThrows(IllegalStateException.class, ()-> fileStore.isAnImage(multipart));
    }



    @Test
    void extractMetadata() {
        // given
        String fileName = "image";
        Path path = Paths.get("D:\\Projekty java\\hotel\\hotel\\src\\test\\resources\\hotel-1.jpg");
        String contentType = "image/jpeg";
        byte[] content = new byte[0];
        try{
            content = Files.readAllBytes(path);
        } catch (IOException e) {

        }
        MultipartFile multipart = new MockMultipartFile(fileName, fileName, contentType, content);

    }

    @Test
    public void putObject() {
        // given
        String bucketName = "ws89-image-upload-123";
        // when
        try{
//            s3.putObject(path, fileName, inputStream, metadata);
        }catch (AmazonServiceException e){
            throw new IllegalStateException("Failed to store file to s3", e);
        }

        // then

    }

    @Test
    void saveThrowsIllegalStateException() {

    }

    @Test
    void download() {
    }

    @Test
    void downloadThrowsIllegalStateException() {

    }
}