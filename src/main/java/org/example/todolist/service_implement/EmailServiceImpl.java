package org.example.todolist.service_implement;

import java.io.File;

import org.example.todolist.model.EmailDetails;
import org.example.todolist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {

        try {

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(details.getRecipient());
            msg.setFrom(sender);
            msg.setSubject(details.getSubject());
            msg.setText(details.getMsgBody());

            javaMailSender.send(msg);
            return "successful";
        }

        catch (Exception e) {
            return e.toString();
        }
    }


}