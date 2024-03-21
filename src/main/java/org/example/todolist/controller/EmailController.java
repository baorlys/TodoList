package org.example.todolist.controller;

import org.example.todolist.model.EmailDetails;
import org.example.todolist.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    // Sending a simple Email
    @PostMapping("/send-mail")
    public String
    sendMail(@RequestBody EmailDetails details)
    {
        return emailService.sendSimpleMail(details);
    }

//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//            @RequestBody EmailDetails details)
//    {
//        return "hi";
//    }
}