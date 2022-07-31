package pl.siuda.hotel.models.enums;

public enum Grade {

    ONESTAR("ONESTAR"), TWOSTARS("TWOSTARS"), THREESTARS("THREESTARS"), FOURSTARS("FOURSTARS"), FIVESTARS("FIVESTARS");

    private final String name;

    Grade(String name) {
        this.name = name;
    }
}
