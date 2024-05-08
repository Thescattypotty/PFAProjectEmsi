package com.example.backend.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Annotation.RequiresPermission;
import com.example.backend.Annotation.RequiresRole;
import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;
import com.example.backend.MapToResponse.ContactMapperResponse;
import com.example.backend.MapToResponse.InteractionMapperResponse;
import com.example.backend.Payload.Response.ContactResponse;
import com.example.backend.Payload.Response.InteractionResponse;
import com.example.backend.Services.ContactService;
import com.example.backend.Services.InteractionService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
@RequiresRole({"ROLE_USER"})
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @Autowired
    private InteractionService interactionService;

    @PostMapping
    @RequiresPermission({"CREATE_CONTACT"})
    public ResponseEntity<ContactResponse> createContact(@RequestBody Contact contactDto)
    {
        Contact savedContactDto = contactService.createContact(contactDto);
        return new ResponseEntity<>(ContactMapperResponse.MapToContactResponse(savedContactDto), HttpStatus.CREATED);
    }
    @PostMapping("/{id}/interaction")
    @RequiresPermission({"CREATE_INTERSECTION","MODIFY_CONTACT"})
    public ResponseEntity<ContactResponse> addInteractionToContact(@PathVariable("id") Long contactId, @RequestBody Interaction interactionDto)
    {
        Contact savedContactDto = contactService.AddInteractionToContact(contactId, interactionDto);
        return new ResponseEntity<>(ContactMapperResponse.MapToContactResponse(savedContactDto), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/interaction/modify")
    @RequiresPermission({ "CREATE_INTERSECTION", "MODIFY_CONTACT","MODIFY_INTESECTION" })
    public ResponseEntity<InteractionResponse> ModifyInteractionToContact(@PathVariable("id") Long interactionId,
            @RequestBody Interaction interactionDto) {

        Interaction UpdatedinteractionDto = interactionService.updateInteraction(interactionId, interactionDto);
        return ResponseEntity.ok(InteractionMapperResponse.mapToInteractionResponse(UpdatedinteractionDto));
    }


    @GetMapping("/{id}")
    @RequiresPermission({ "ACCESS_CONTACT" })
    public ResponseEntity<ContactResponse> getContactById(@PathVariable("id") Long contactId)
    {
        Contact contactDto = contactService.getContactById(contactId);
        return ResponseEntity.ok(ContactMapperResponse.MapToContactResponse(contactDto));
    }
    
    @GetMapping
    @RequiresPermission({ "ACCESS_CONTACT" })
    public ResponseEntity<List<ContactResponse>> getAllcontact() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(
            contacts.stream().map(ContactMapperResponse::MapToContactResponse)
            .collect(Collectors.toList())
        );
    }
    
    @PutMapping("/{id}")
    @RequiresPermission({ "MODIFY_CONTACT" })
    public ResponseEntity<ContactResponse> updatecontact(@PathVariable("id") Long contactId,
            @RequestBody Contact contactDto) {
        Contact contact = contactService.updateContact(contactId, contactDto);
        return ResponseEntity.ok(ContactMapperResponse.MapToContactResponse(contact));
    }

    @DeleteMapping("/{id}")
    @RequiresPermission({ "DELETE_CONTACT" })
    public ResponseEntity<String> deletecontact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.ok("contact Deleted Successfully !.");
    }
}
