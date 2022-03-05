package pl.siuda.hotel.amazonS3bucket;

import org.springframework.data.annotation.Id;

import java.util.Objects;

public class Image {

    @Id
    private Long imageId;
    private String imageLink;

    public Image(String imageLink) {
        this.imageLink = imageLink;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(imageId, image.imageId) &&
                Objects.equals(imageLink, image.imageLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageId, imageLink);
    }
}
