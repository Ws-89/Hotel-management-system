package pl.siuda.hotel.registration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailValidatorTest {

    @Autowired
    private EmailValidator emailValidator;

    @Test
    void emailIsCorrect() {
        // given
        String email = "wiktorsiuda@gmail.com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isTrue();
    }

    @Test
    void emailWithTwoMonkeysIsIncorrect() {
        // given
        String email = "wiktorsiuda@@gmail.com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isFalse();
    }

    @Test
    void emailWithForthSlashIsIncorrect() {
        // given
        String email = "wiktorsiuda@gmail./com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isFalse();
    }

    @Test
    void EmailStartsWithQuestionMarkIsFalse() {
        // given
        String email = "?wiktorsiuda@gmail.com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isFalse();
    }

    @Test
    void EmailWithQuestionMarkInFirstPartIsFalse() {
        // given
        String email = "wiktor?iuda@gmail.com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isFalse();
    }

    @Test
    void EmailWithAllowedSpecialCharactersIsTrue() {
        // given
        String email = "wiktor_+&*-siuda@gmail.com";
        // when
        boolean expected = emailValidator.test(email);

        assertThat(expected).isTrue();
    }
}