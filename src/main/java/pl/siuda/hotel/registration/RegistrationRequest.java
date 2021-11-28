package pl.siuda.hotel.registration;

import java.util.Objects;

public class RegistrationRequest {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String password;
    private final String street;
    private final String city;
    private final String state;
    private final String country;
    private final String zipcode;
    private final String phoneNumber;

    public RegistrationRequest(String firstName, String lastName, String email, String password, String street, String city, String state, String country, String zipcode, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationRequest that = (RegistrationRequest) o;
        return firstName.equals(that.firstName) && lastName.equals(that.lastName) && email.equals(that.email) && password.equals(that.password) && street.equals(that.street) && city.equals(that.city) && state.equals(that.state) && country.equals(that.country) && zipcode.equals(that.zipcode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, password, street, city, state, country, zipcode);
    }

    @Override
    public String toString() {
        return "RegistrationRequest{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}
