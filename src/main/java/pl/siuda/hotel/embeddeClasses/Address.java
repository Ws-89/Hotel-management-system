package pl.siuda.hotel.embeddeClasses;

import org.springframework.data.relational.core.mapping.Column;

public class Address {

    @Column("STREET")
    private String street;
    @Column("CITY")
    private String city;
    @Column("STATE")
    private String state;
    @Column("COUNTRY")
    private String country;
    @Column("ZIPCODE")
    private String zipcode;

    public Address() {
    }

    public Address(String street, String city, String state, String country, String zipcode) {

        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
