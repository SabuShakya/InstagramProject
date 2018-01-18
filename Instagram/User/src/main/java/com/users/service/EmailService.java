package com.users.service;

import com.users.model.User;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service("emailService")
public class EmailService {

    private JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
//    }
//    @Autowired
//    private UserService userService;
//
//    public void sendEmail(SimpleMailMessage email) {
//
//
////        String recipientAddress = userService.getUserEmail();
////        String subject = "Registration Confirmation";
////        String confirmationUrl
////                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
////        String message = messages.getMessage("message.regSucc", null, event.getLocale());
////
////        SimpleMailMessage email = new SimpleMailMessage();
////        email.setTo(recipientAddress);
////        email.setSubject(subject);
////        email.setText(message + " rn" + "http://localhost:8080" + confirmationUrl);
////        mailSender.send(email);
//        SimpleMailMessage registrationEmail = new SimpleMailMessage();
//        registrationEmail.setTo(userService.getUserEmail(email));
//
//        registrationEmail.setSubject("Registration Confirmation");
//        registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
//                + appUrl + "/confirm?token=" + user.getConfirmationToken());
//        registrationEmail.setFrom("noreply@domain.com");
//
//        emailService.sendEmail(registrationEmail);
//
//        modelAndView.addObject("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
//        modelAndView.setViewName("register");
    }
}