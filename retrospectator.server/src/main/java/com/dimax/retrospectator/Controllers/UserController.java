package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.TeamMaker;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.UserService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class UserController {
    @Autowired
    UserService service;


    @JsonView({TeamMaker.Team.class})
    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(service.getUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<User> getUserById(@RequestBody User user) {
        return ResponseEntity.ok().body(service.getUser(user));
    }
}
