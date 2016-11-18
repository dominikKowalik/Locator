package com.dominik.kowalik.web;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import com.dominik.kowalik.Helpers.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SMTPMailSender {

    /**
     * wstrzykniecie obiektu klasy JavaMailSender skonfigurowanego w application.propoerties
     */
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * wysyłanie maila, tworzenie obiektu MIME - Multipurpose Internet Mail Extensions ustawienie \n
     * odbiorcy, tematu i treści wiadomości
     * @param to
     * @param subject
     * @param body
     * @throws MessagingException
     */

    public void send(String to, String subject, String body) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        helper = new MimeMessageHelper(message, true);
        helper.setSubject(subject);
        helper.setTo(to);
        helper.setText(body, true);

        javaMailSender.send(message);
    }

    public void sendPasswordRemind(String to, String randomPassword) throws MessagingException {
        send(to, "Przypominienie hasła w aplikacji lokalizator", "Nowe hasło:" + randomPassword);
    }


}
