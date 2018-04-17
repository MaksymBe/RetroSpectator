package com.dimax.retrospectator;


import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.TeamMaker;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/retro")
@CrossOrigin(origins = "*")
public class Controller {

    @Autowired
    RepositoryRetro repositoryRetro;

    @JsonView({TeamMaker.Team.class})
    @GetMapping(name = "")
    public ResponseEntity<Team[]> getAllGroups() {
        return ResponseEntity.ok().body(repositoryRetro.get());
    }


}
