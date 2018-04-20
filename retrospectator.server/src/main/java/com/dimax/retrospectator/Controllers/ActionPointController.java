package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.ActionPoint;
import com.dimax.retrospectator.Services.ActionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-point")
@CrossOrigin(origins = "*")
public class ActionPointController {
    @Autowired
    ActionPointService actionPointRepository;

    @PostMapping("/{identifier}")
    public ResponseEntity<ActionPoint> createActionPoint(@PathVariable String identifier, @RequestBody ActionPoint actionPoint){
        ActionPoint createdActionPoint = actionPointRepository.createActionPoint(actionPoint, identifier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActionPoint);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<List<ActionPoint>> getActionPointForTeam(@PathVariable String identifier){
        List<ActionPoint> actionPointsForTeam = actionPointRepository.getActionPointsForTeam(identifier);
        return ResponseEntity.ok(actionPointsForTeam);
    }

    @GetMapping("/{identifier}/{retroId}")
    public ResponseEntity<List<ActionPoint>> getActionPointForRetro(@PathVariable String identifier, @PathVariable int retroId){
        List<ActionPoint> actionPointsForRetro = actionPointRepository.getActionPointsForRetro(retroId, identifier);
        if(actionPointsForRetro == null){
            ResponseEntity.status(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(actionPointsForRetro);
    }
}
