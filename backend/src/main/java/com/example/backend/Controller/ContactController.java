package com.example.backend.Controller;

import java.util.List;

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
import com.example.backend.EntityDto.ContactDto;
import com.example.backend.EntityDto.InteractionDto;
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
    public ResponseEntity<ContactDto> createContact(@RequestBody ContactDto contactDto)
    {
        ContactDto savedContactDto = contactService.createContact(contactDto);
        return new ResponseEntity<>(savedContactDto, HttpStatus.CREATED);
    }
    @PostMapping("/{id}/interaction")
    @RequiresPermission({"CREATE_INTERSECTION","MODIFY_CONTACT"})
    public ResponseEntity<ContactDto> addInteractionToContact(@PathVariable("id") Long contactId, @RequestBody InteractionDto interactionDto)
    {
        ContactDto savedContactDto = contactService.AddInteractionToContact(contactId, interactionDto);
        return new ResponseEntity<>(savedContactDto, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/interaction/modify")
    @RequiresPermission({ "CREATE_INTERSECTION", "MODIFY_CONTACT","MODIFY_INTESECTION" })
    public ResponseEntity<InteractionDto> ModifyInteractionToContact(@PathVariable("id") Long interactionId,
            @RequestBody InteractionDto interactionDto) {

        InteractionDto UpdatedinteractionDto = interactionService.updateInteraction(interactionId, interactionDto);
        return ResponseEntity.ok(UpdatedinteractionDto);
    }


    @GetMapping("{id}")
    @RequiresPermission({ "ACCESS_CONTACT" })
    public ResponseEntity<ContactDto> getContactById(@PathVariable("id") Long contactId)
    {
        ContactDto contactDto = contactService.getContactById(contactId);
        return ResponseEntity.ok(contactDto);
    }
    
    @GetMapping
    @RequiresPermission({ "ACCESS_CONTACT" })
    public ResponseEntity<List<ContactDto>> getAllcontact() {
        List<ContactDto> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }
    
    @PutMapping("{id}")
    @RequiresPermission({ "MODIFY_CONTACT" })
    public ResponseEntity<ContactDto> updatecontact(@PathVariable("id") Long contactId,
            @RequestBody ContactDto contactDto) {
        ContactDto contact = contactService.updateContact(contactId, contactDto);
        return ResponseEntity.ok(contact);
    }

    @DeleteMapping("{id}")
    @RequiresPermission({ "DELETE_CONTACT" })
    public ResponseEntity<String> deletecontact(@PathVariable("id") Long contactId) {
        contactService.deleteContact(contactId);
        return ResponseEntity.ok("contact Deleted Successfully !.");
    }
}
