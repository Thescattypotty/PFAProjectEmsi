package com.example.backend.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/twiml")
public class TwimlController {
    @GetMapping
    public String getTwiML() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<Hangup />";    
        }
}
