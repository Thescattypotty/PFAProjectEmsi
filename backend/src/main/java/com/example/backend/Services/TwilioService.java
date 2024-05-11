package com.example.backend.Services;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;

import com.example.backend.Exception.TwilioApiException;
import com.example.backend.IServices.ITwilioService;
import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.twilio.type.Twiml;

public class TwilioService implements ITwilioService
{
    @Value("${twilio.accountSid}")
    private String twilio_account_id;

    @Value("${twilio.authToken}")
    private String twilioAuthToken;

    @Value("${twilio.phoneNumber}")
    private String twilioPhoneNumber;


    @Override
    public void sendSms(String phoneNumber, String message) {       
        try {
            Twilio.init(twilio_account_id, twilioAuthToken);
            if(isPhoneNumberValid(phoneNumber))
            {
                PhoneNumber to = new PhoneNumber(phoneNumber);
                PhoneNumber from = new PhoneNumber(twilioPhoneNumber);
                Message twilioMessage = Message.creator(to, from, message).create();
                if(!twilioMessage.getStatus().equals("queued"))
                {
                    throw new TwilioApiException("Erreur will sending sms , status : "+ twilioMessage.getStatus().toString());
                }

            }else{
                throw new TwilioApiException("The phone number is not valid");
            }
        } catch (ApiException e) {
            throw new TwilioApiException("Error while sending sms");
        }
        catch (Exception e) {
            throw new RuntimeException("An unexpected error occured.");
        }
    }
    
    
    @Override
    public void makeCall(String phoneNumber, String url) {
        try {
            Twilio.init(twilio_account_id, twilioAuthToken);
            if (isPhoneNumberValid(phoneNumber)) {
                PhoneNumber to = new PhoneNumber(phoneNumber);
                PhoneNumber from = new PhoneNumber(twilioPhoneNumber);
                Twiml twimlUrl = new Twiml(url);
                Call call = Call.creator(to, from, twimlUrl).create();
                System.out.println("Call initiated successfully with SID : " + call.getSid());
            }
            else{
                throw new TwilioApiException("The phone number is not valid"); 
            }
        } catch (ApiException e) {
            throw new TwilioApiException("Error while calling customer");
        }
        catch(Exception e){
            throw new RuntimeException("An unexpected error occured.");
        }
        
    }


    private boolean isPhoneNumberValid(String phoneNumber)
    {
        String phoneRegex = "^\\+?[0-9]+$";

        Pattern pattern = Pattern.compile(phoneRegex);

        return phoneNumber!= null && pattern.matcher(phoneNumber).matches();
    }

}
