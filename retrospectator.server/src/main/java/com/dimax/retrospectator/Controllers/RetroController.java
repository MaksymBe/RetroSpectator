package com.dimax.retrospectator.Controllers;


import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.RetroRepository;
import com.dimax.retrospectator.Services.RetroService;
import com.dimax.retrospectator.Services.TeamRepository;
import com.dimax.retrospectator.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/retro")
@CrossOrigin(origins = "*")
public class RetroController {

    @Autowired
    RetroService retroService;
    @Autowired
    TeamService teamService;
    @Autowired
    RetroRepository retroRepository;

    @GetMapping("/{identifier}")
    public ResponseEntity<Retro> getCurrentRetro(@PathVariable String identifier){
        Retro retro = retroService.getCurrentRetro(identifier);
        return ResponseEntity.ok(retro);
    }

    @GetMapping("/{identifier}/{id}")
    public ResponseEntity<Retro> getRetroById(@PathVariable String identifier, @PathVariable int id){
        Retro retro = retroService.getRetroById(id);
        return ResponseEntity.ok(retro);
    }

    @GetMapping("/{identifier}/all")
    public ResponseEntity<List<Retro>> getRetrosByTeam(@PathVariable String identifier, ServletRequest servletRequest) {
        Team team = teamService.getTeamById(identifier, (User)servletRequest.getAttribute("user"));
        List<Retro> retros = retroRepository.getAllByTeam(team);
        return ResponseEntity.ok().body(retros);
    }

    @PatchMapping("/{identifier}/finish")
    public ResponseEntity<Retro> startNewRetro(@Valid @RequestBody String impression, @PathVariable String identifier) {
        Retro retro = retroService.startNewRetro(identifier, impression);
        return ResponseEntity.ok(retro);
    }


}
