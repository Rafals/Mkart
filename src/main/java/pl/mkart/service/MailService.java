package pl.mkart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendActivationEmail(String to, String code) {
        String link = "http://localhost:8080/activate?code=" + code;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Aktywacja konta");
        message.setText("Kliknij, aby aktywować konto: " + link);
        mailSender.send(message);
    }
}
