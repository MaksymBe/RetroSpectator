package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.ActionPoint;
import com.dimax.retrospectator.Services.ActionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/action-point")
@CrossOrigin(origins = "*")
public class ActionPointController {

    @Autowired
    ActionPointService actionPointService;

    @PostMapping("/{identifier}")
    public ResponseEntity<ActionPoint> createActionPoint(@PathVariable String identifier, @Valid @RequestBody ActionPoint actionPoint){
        ActionPoint createdActionPoint = actionPointService.createActionPoint(actionPoint, identifier);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdActionPoint);
    }

    @GetMapping("/{identifier}")
    public ResponseEntity<List<ActionPoint>> getActionPointForTeam(@PathVariable String identifier){
        List<ActionPoint> actionPointsForTeam = actionPointService.getActionPointsForTeam(identifier);
        return ResponseEntity.ok(actionPointsForTeam);
    }

    @DeleteMapping("/{pointId}")
    public ResponseEntity<ActionPoint> deleteActionPoint(@PathVariable int pointId) {
        ActionPoint actionPoint = actionPointService.deleteActionPointById(pointId);

        return ResponseEntity.ok(actionPoint);
    }

    @PatchMapping("/{pointId}")
    public ResponseEntity<ActionPoint> updateActionPoint(@Valid @RequestBody ActionPoint actionPoint, @PathVariable int pointId) {
       ActionPoint updatedActionPoint = actionPointService.updateActionPointById(actionPoint, pointId);

       if(updatedActionPoint == null) {
           return ResponseEntity.notFound().build();
       }

       return ResponseEntity.ok(updatedActionPoint);
    }

    @GetMapping("/retro/{retroId}")
    public ResponseEntity<List<ActionPoint>> getActionPointsByRetro(@PathVariable int retroId) {
        List<ActionPoint> actionPoints = actionPointService.getActionPointsForRetro(retroId);

        return ResponseEntity.ok().body(actionPoints);
    }
}
