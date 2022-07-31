package pl.siuda.hotel.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.siuda.hotel.util.EmailValidator;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

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