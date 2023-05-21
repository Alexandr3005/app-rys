

package com.app.rys.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//registrar y logear usuario

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.rys.models.User;
import com.app.rys.repository.BookingRepository;
import com.app.rys.repository.BuildingRepository;
import com.app.rys.repository.SeatRepository;
import com.app.rys.repository.UserRepository;
import com.app.rys.service.ISeatService;
import com.app.rys.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;
	private Long userId;

    @PostMapping("/login/")
	public ResponseEntity<User> loginUser(@RequestBody User userData) {
	    User user = userRepository.findByEmail(userData.getEmail());

	    if (user != null && user.getPassword().equals(userData.getPassword())) {
	    	 userId = user.getId();
	    	 
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.status(500).build();
	    }
	}
	
    

	@PostMapping("/register/")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
	    // Verificar si el correo ya existe
		
	    User existingUser = userRepository.findByEmail(user.getEmail());
	    if (existingUser != null) {
	        return ResponseEntity.badRequest().body("Email already exists");
	    }
	    
	    try {
	        User savedUser = userRepository.save(user);
	        
	     // Almacenar usuario en la base de datos
	        
	        userId = savedUser.getId(); 
	        return ResponseEntity.ok(savedUser);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(500).build();
	    }
	}
	
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userData, 
										   HttpServletRequest request) {
	    // Obtener y actualizar usuario
		
	    Optional<User> optionalUser = userRepository.findById(id);
	    if (optionalUser.isPresent()) {
	        User user = optionalUser.get();
	        user.setFullName(userData.getFullName());
	        user.setPassword(userData.getPassword());
	        user.setConfirmPassword(userData.getConfirmPassword());
	        user.setPhone(userData.getPhone());
	    
	        userRepository.save(user);
	        return ResponseEntity.ok(user);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}


