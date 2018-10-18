package com.dean.reptile.mail;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class Email {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${email.receivers.address}")
    String receivers;

    @Value("${spring.mail.username}")
    String from;

    @Value("${email.receivers.nickname}")
    String nickname;

    private static final String SUBJECT = "爬虫邮件通知";

    public void sendSimpleMail(String[] to, String from, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(StringUtils.isBlank(subject) ? SUBJECT : subject);
        message.setText(text);

        mailSender.send(message);
    }

    public void sendEmail(String subject, String text) {
        String[] address = receivers.split(";");
        sendSimpleMail(address, getDefaultNickname(), subject, text);
    }

    public String getDefaultNickname(){
        return getNickname(nickname, from);
    }

    public static String getNickname(String nickname, String address) {
        return nickname + "<" + address + ">";
    }
}
