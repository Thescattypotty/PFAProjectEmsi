package com.example.backend.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.example.backend.Exception.MailNotSentException;
import com.example.backend.IServices.IMailService;
import com.example.backend.Payload.Request.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.File;

@Service
public class MailService implements IMailService
{
    

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @Override
    public void sendEmail(EmailRequest emailRequest) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(emailRequest.getRecipient());
            mailMessage.setText(emailRequest.getBody());
            mailMessage.setSubject(emailRequest.getSubject());


            mailSender.send(mailMessage);

        } catch (Exception e) {
            throw new MailNotSentException("Error while sending email");
        }
    }

    @Override
    public void sendEmailWithAttechement(EmailRequest emailRequest) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(emailRequest.getRecipient());
            mimeMessageHelper.setText(emailRequest.getBody());
            mimeMessageHelper.setSubject(emailRequest.getSubject());


            FileSystemResource file = new FileSystemResource(new File(emailRequest.getAttachement()));
            mimeMessageHelper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);

        } catch (MessagingException e) {
            throw new MailNotSentException("Error while sending email");
        }
        
    }


    
}
