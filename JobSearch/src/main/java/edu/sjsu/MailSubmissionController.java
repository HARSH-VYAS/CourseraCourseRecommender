package edu.sjsu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class MailSubmissionController {

    private final JavaMailSender javaMailSender;
    @Autowired
    MailSubmissionController(JavaMailSender javaMailSender)
    {
        this.javaMailSender = javaMailSender;
    }

    @RequestMapping("/mail")
    @ResponseStatus(HttpStatus.CREATED)
    SimpleMailMessage send() {        
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(LinkedInController.m_ToUser);
        mailMessage.setReplyTo(LinkedInController.m_ToUser);
        mailMessage.setFrom("cmpe273linkedera@gmail.com");
        mailMessage.setSubject("New Course added in Coursera");
        mailMessage.setText("Hello " + LinkedInController.m_Username + ". New courses have been added. The names of the course(s) are " );
        javaMailSender.send(mailMessage);
        return mailMessage;
    }
}
