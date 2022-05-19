package com.dziwisz.offerts.pl.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailSenderService {
    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String toEmail,
                          String body){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("acounttolearn123@gmail.com");
        mailMessage.setTo(toEmail);
        mailMessage.setText(body);
        mailMessage.setSubject("This companies have offerts to intership in Java\n\n");

        javaMailSender.send(mailMessage);

        System.out.println("Mail sent success ...");
    }

    public String mailFormat(List<String> text) {
        return String.join("\n\n", text);
    }
}
