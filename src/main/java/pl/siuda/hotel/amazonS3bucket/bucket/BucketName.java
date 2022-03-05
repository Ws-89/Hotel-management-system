package pl.siuda.hotel.amazonS3bucket.bucket;

public enum BucketName {
    PROFILE_IMAGE("ws89-image-upload-123");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
