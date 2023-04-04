
/*
package com.app.rys.controller;

import javax.servlet.http.HttpSession;

//registrar y logear usuario

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.rys.models.User;
import com.app.rys.repository.UserRepository;

@RestController
@RequestMapping("/api/reservation")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {
	@Autowired
	private UserRepository repository;
	
	private Long userId;
	
	
	@PostMapping("/login/")
	public ResponseEntity<User> loginUser(@RequestBody User userData, HttpSession session) {
	    User user = repository.findByEmail(userData.getEmail());

	    if (user != null && user.getPassword().equals(userData.getPassword())) {
	        userData.setId(user.getId());
	        session.setAttribute("userId", user.getId()); // Agregar userId a la sesión
	        return ResponseEntity.ok(userData);
	    } else {
	        return ResponseEntity.status(500).build();
	    }
	}

	
	@PostMapping("/register/")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return ResponseEntity.ok(repository.save(user));
	}
	
}
*/