package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.TeamMaker;
import com.dimax.retrospectator.Services.TeamService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping(value = "/team")
@CrossOrigin(origins = "*")
public class TeamController {
    @Autowired
    TeamService service;

    @JsonView({TeamMaker.Team.class})
    @GetMapping("/{id}")

    public ResponseEntity<Team> getTeamById(@PathVariable String id) {
        return ResponseEntity.ok().body(service.getTeamById(id));
    }

    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestBody Team body){
        return ResponseEntity.ok().body(service.saveTeam(body));
    }

    @GetMapping("")
    public ResponseEntity<List<Team>> getTeamById(Principal principal, Authentication a) {
        System.out.println(principal);
        return ResponseEntity.ok().body(service.getTeam());
    }
}
