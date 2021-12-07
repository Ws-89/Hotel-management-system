package pl.siuda.hotel.email;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.*;

import com.icegreen.greenmail.user.UserManager;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmailServiceTest {

    @Autowired
    private JavaMailSender emailSender;

    @Test
    void send() throws MessagingException, IOException {
    // given

        ServerSetup setup = new ServerSetup(1025, "localhost", "smtp");
        GreenMail greenMail = new GreenMail(setup);
        greenMail.start();

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText("text message");
        helper.setTo("test@receiver.com");
        helper.setFrom("hello@mails.pl");
        helper.setSubject("subject");

        String text = "text message";
        String to = "test@receiver.com";
        String from = "hello@mails.pl";
        String subject = "subject";
    // when
        emailSender.send(mimeMessage);

    // then
        MimeMessage[] receivedMessages = greenMail.getReceivedMessages();
        assertThat(receivedMessages.length).isEqualTo(1);

        MimeMessage receivedMessage = receivedMessages[0];
        assertThat(receivedMessage.getAllRecipients()[0].toString()).isEqualTo(to); //to
        assertThat(receivedMessage.getFrom()[0].toString()).isEqualTo(from); //from
        assertThat(receivedMessage.getSubject()).isEqualTo(subject); //title
        assertThat(receivedMessage.getContent().toString()).contains((text)); //content
        //Or
        assertThat(helper.getMimeMessage().getContent()).isEqualTo(GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]));
    }
}