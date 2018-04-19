package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.TeamService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/team")
@CrossOrigin(origins = "*")
@Scope("request")
public class TeamController {

    @Autowired
    TeamService service;

    @GetMapping("/{id}")

    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getTeamById(id));
    }

    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestBody Team body, ServletRequest request){
        User user = (User) request.getAttribute("user");

        Team team = service.saveTeam(body, user);


        return ResponseEntity.ok().body(team);
    }


    @GetMapping("")
    public ResponseEntity<List<Team>> getTeamById(ServletRequest request, Principal principal, Authentication a) {

        System.out.println(principal);
        return ResponseEntity.ok().body(service.getTeam());
    }
}
