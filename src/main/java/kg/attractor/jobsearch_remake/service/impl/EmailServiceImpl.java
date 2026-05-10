package kg.attractor.jobsearch_remake.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kg.attractor.jobsearch_remake.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final MessageSource messageSource;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    @Override
    public void send(String to, String link, Locale locale)
            throws MessagingException, UnsupportedEncodingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(EMAIL_FROM, "JobSearch Support");
        helper.setTo(to);

        String subject = messageSource.getMessage("email.reset.subject", null, locale);
        String greeting = messageSource.getMessage("email.reset.greeting", null, locale);
        String body = messageSource.getMessage("email.reset.body", null, locale);
        String linkText = messageSource.getMessage("email.reset.link", null, locale);
        String ignore = messageSource.getMessage("email.reset.ignore", null, locale);

        String content = "<p>" + greeting + "</p>"
                + "<p>" + body + "</p>"
                + "<p><a href=\"" + link + "\">" + linkText + "</a></p>"
                + "<br>"
                + "<p>" + ignore + "</p>";

        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}