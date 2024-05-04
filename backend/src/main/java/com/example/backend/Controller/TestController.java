package com.example.backend.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.Annotation.RequiresRole;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

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
}
