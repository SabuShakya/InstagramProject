package com.users.service;

import com.users.dto.Userdto;
import com.users.model.User;
import com.users.utils.TokenUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service("emailService")
public class EmailService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user)throws MailException{
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setFrom("mool.smreeti@gmail.com");
        mailMessage.setSubject("Thank you for registering on Instagram " +user.getUsername()+"....");
        mailMessage.setText(" Dear " +user.getEmail() +" activate your account by using this password: "
        +user.getPassword());
        javaMailSender.send(mailMessage);
    }
}