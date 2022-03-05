package pl.siuda.hotel.registration;

import java.util.function.Predicate;

public class PhoneNumberValidator implements Predicate<String> {
    @Override
    public boolean test(String s) {
        return s.length() == 9;
    }
}
