
/*
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

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ISeatService seatService;

    private Long getUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new RuntimeException("User not found");
        }
        return userId;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> loginUser(@RequestBody User userData, HttpSession session) {
        User user = repository.findByEmail(userData.getEmail());
        if (user != null && user.getPassword().equals(userData.getPassword())) {
            session.setAttribute("userId", user.getId());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User existingUser = repository.findByEmail(user.getEmail());
        if (existingUser != null) {
            return ResponseEntity.badRequest().body("Email already exists");
        }

        try {
            User savedUser = repository.save(user);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userData, HttpServletRequest request) {
        Optional<User> optionalUser = repository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setFullName(userData.getFullName());
            user.setPassword(userData.getPassword());
            user.setConfirmPassword(userData.getConfirmPassword());
            user.setPhone(userData.getPhone());
            repository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}


*/