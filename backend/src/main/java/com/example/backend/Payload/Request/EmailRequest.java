package com.example.backend.Payload.Request;

public class EmailRequest {
    private String recipient;
    private String subject;
    private String body;
    private String attachement;

    public EmailRequest(String recipient, String subject, String body) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
    }

    public EmailRequest(String recipient, String subject, String body, String attachement) {
        this.recipient = recipient;
        this.subject = subject;
        this.body = body;
        this.attachement = attachement;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAttachement() {
        return attachement;
    }

    public void setAttachement(String attachement) {
        this.attachement = attachement;
    }

}
