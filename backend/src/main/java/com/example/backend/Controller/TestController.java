package com.example.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Annotation.RequiresRole;
import com.example.backend.Entity.Company;
import com.example.backend.Entity.Contact;
import com.example.backend.Entity.Interaction;
import com.example.backend.EntityRepository.CompanyRepository;
import com.example.backend.EntityRepository.ContactRepository;
import com.example.backend.EntityRepository.InteractionRepository;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {


	@Autowired
	private CompanyRepository companyRepository;
	@Autowired 
	private ContactRepository contactRepository;
	@Autowired
	private InteractionRepository interactionRepository;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	@GetMapping("/user")
	@RequiresRole({ "ROLE_USER" })
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@RequiresRole({ "ROLE_MODERATOR" })
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	@RequiresRole({ "ROLE_ADMIN" })
	public String adminAccess() {
		return "Admin Board.";
	}

	@GetMapping("/contacts")
	@RequiresRole({ "ROLE_USER" })
	public ResponseEntity<List<Contact>> getContacts()
	{
		List<Contact> contacts = contactRepository.findAll();
		return ResponseEntity.ok(contacts);
	}

	@GetMapping("/companys")
	@RequiresRole({"ROLE_USER"})
	public ResponseEntity<List<Company>> getCompanys() {
		List<Company> compans = companyRepository.findAll();
		return ResponseEntity.ok(compans);
	}


	@GetMapping("/interactions")
	@RequiresRole({ "ROLE_USER" })
	public ResponseEntity<List<Interaction>> getInteractions() {
		List<Interaction> interactions = interactionRepository.findAll();
		return ResponseEntity.ok(interactions);
	}
}
