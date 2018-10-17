package com.dean.reptile.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
    @Autowired
    private JavaMailSender mailSender;

    private static final String SUBJECT = "爬虫邮件通知";

    public void sendSimpleMail(String[] to, String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(StringUtils.isBlank(subject) ? SUBJECT : subject);
        message.setText(text);

        mailSender.send(message);
    }
}
