package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Set;


@RestController
@RequestMapping(value = "/team")
@CrossOrigin(origins = "*")
public class TeamController {

    @Autowired
    TeamService teamService;

    @GetMapping("/{identifier}")
    public ResponseEntity<Team> getTeamById(@PathVariable String identifier, ServletRequest request) {
       User user = (User)request.getAttribute("user");
        Team teamById = teamService.getTeamById(identifier, user);
        return ResponseEntity.ok().body(teamById);
    }

    @PostMapping("")
    public ResponseEntity<Team> createTeam(@RequestBody Team body, ServletRequest request){
        User user = (User) request.getAttribute("user");
        Team team = teamService.saveTeam(body, user);
        return ResponseEntity.ok().body(team);
    }


    @GetMapping("/my")
    public ResponseEntity<Set<Team>> getTeam(ServletRequest request) {
            User user = (User) request.getAttribute("user");
        return ResponseEntity.ok().body(user.getTeam());
    }

    @PatchMapping("/{identifier")
    public ResponseEntity<Team> updateTeam(@Valid @RequestBody Team team, @PathVariable int id) {
        Team updatedTeam = teamService.updateTeamById(team,id);

        if(updatedTeam == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedTeam);
    }
}
