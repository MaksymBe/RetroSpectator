package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.ActionPoint;
import com.dimax.retrospectator.Services.ActionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/action-point")
@CrossOrigin(origins = "*")
public class ActionPointController {
    @Autowired
    ActionPointService actionPointRepository;

    @PostMapping("/{identifier}")
    public ActionPoint createActionPoint(@PathVariable String identifier, @RequestBody ActionPoint actionPoint){
        return actionPointRepository.createActionPoint(actionPoint, identifier);
    }

    @GetMapping("/{identifier}")
    public List<ActionPoint> getActionPointForTeam(@PathVariable String identifier){
        return  actionPointRepository.getActionPointsForTeam(identifier);
    }

    @GetMapping("/{retroId}")
    public List<ActionPoint> getActionPointForRetro(@PathVariable int retroId){
        return actionPointRepository.getActionPointsForRetro(retroId);
    }
}
