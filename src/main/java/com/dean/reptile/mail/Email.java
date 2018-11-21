package com.dean.reptile.mail;

import com.dean.reptile.service.impl.C5JewelrySpider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static Logger log = LoggerFactory.getLogger(Email.class);

    public void sendSimpleMail(String[] to, String from, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject(StringUtils.isBlank(subject) ? SUBJECT : subject);
            message.setText(text);

            mailSender.send(message);
        } catch (Exception e) {
            log.error("email send fail!", e);
        }

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
