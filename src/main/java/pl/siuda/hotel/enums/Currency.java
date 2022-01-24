package pl.siuda.hotel.enums;

public enum Currency {

    GBP("GBP"), USD("USD"), PLN("PLN"), EUR("EUR"), CHF("CHF");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }
}
