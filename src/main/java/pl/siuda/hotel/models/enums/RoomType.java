package pl.siuda.hotel.models.enums;

public enum RoomType {

    SINGLE("SINGLE"), DOUBLE("DOUBLE"), TRIPLE("TRIPLE"), QUAD("QUAD"), QUEEN("QUEEN"), KING("KING"), DOUBLEDOUBLE("DOUBLEDOUBLE");

    private final String name;


    RoomType(String name) {
        this.name = name;
    }
}
