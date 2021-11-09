package pl.siuda.hotel.embeddeClasses;

import org.springframework.data.relational.core.mapping.Column;

public class Contact {

    @Column("PHONE_NUMBER")
    private String phoneNumber;
    @Column("EMAIL")
    private String email;

    public Contact(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
