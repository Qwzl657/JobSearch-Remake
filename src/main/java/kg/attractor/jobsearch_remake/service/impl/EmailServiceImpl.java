package kg.attractor.jobsearch_remake.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kg.attractor.jobsearch_remake.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    @Override
    public void send(String to, String link)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(EMAIL_FROM, "JobSearch Support");
        helper.setTo(to);

        String subject = "Ссылка для сброса пароля";
        String content = "<p>Здравствуйте,</p>"
                + "<p>Вы запросили сброс пароля.</p>"
                + "<p>Нажмите на ссылку ниже чтобы изменить пароль:</p>"
                + "<p><a href=\"" + link + "\">Изменить пароль</a></p>"
                + "<br>"
                + "<p>Если вы не запрашивали сброс — просто проигнорируйте это письмо.</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}