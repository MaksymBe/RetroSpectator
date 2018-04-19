package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    UserService service;


    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.getUserById(id));
    }

//    @PostMapping("/user")
//    public ResponseEntity<User> getUserById(@RequestBody User user) {
//        return ResponseEntity.ok().body(service.getUser(user));
//    }
}
