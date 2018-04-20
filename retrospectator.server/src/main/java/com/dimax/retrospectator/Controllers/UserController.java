package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Services.TeamService;
import com.dimax.retrospectator.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService service;

    @Autowired
    TeamService teamRepository;


    @GetMapping("/me")
    public ResponseEntity getUserById(ServletRequest request) {
        return ResponseEntity.ok().body(request.getAttribute("user"));
    }

    @GetMapping("/{identifier}/all")
    public ResponseEntity getAllUserForTeam(@PathVariable String identifier, ServletRequest request) {
        System.out.println(identifier);
        return ResponseEntity.ok().body(teamRepository.getUsersForCurrentTeam(identifier));
    }

//    @PostMapping("/user")
//    public ResponseEntity<User> getUserById(@RequestBody User user) {
//        return ResponseEntity.ok().body(service.getUser(user));
//    }
}
