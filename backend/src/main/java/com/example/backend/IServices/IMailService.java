package com.example.backend.IServices;

import com.example.backend.Payload.Request.EmailRequest;

public interface IMailService {
    
    void sendEmail(EmailRequest emailRequest);

    void sendEmailWithAttechement(EmailRequest emailRequest);

    
}
