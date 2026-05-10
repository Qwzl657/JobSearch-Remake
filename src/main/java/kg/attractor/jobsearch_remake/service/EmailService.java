package kg.attractor.jobsearch_remake.service;

import jakarta.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public interface EmailService {
    void send(String to, String link, Locale locale)
            throws MessagingException, UnsupportedEncodingException;
}