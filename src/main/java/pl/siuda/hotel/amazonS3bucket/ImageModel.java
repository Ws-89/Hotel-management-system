package pl.siuda.hotel.amazonS3bucket;

public class ImageModel {
    private byte[] bytePic;

    public ImageModel(byte[] bytePic) {
        this.bytePic = bytePic;
    }

    public byte[] getBytePic() {
        return bytePic;
    }

    public void setBytePic(byte[] bytePic) {
        this.bytePic = bytePic;
    }
}
