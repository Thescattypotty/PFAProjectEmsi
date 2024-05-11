package com.example.backend.IServices;

public interface ITwilioService {
    void sendSms(String phoneNumber , String message);
    void makeCall(String to , String url);
}

