package com.dimax.retrospectator.Controllers;

import com.dimax.retrospectator.Entity.ActionPoint;
import com.dimax.retrospectator.Services.ActionPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actionpoint")
@CrossOrigin(origins = "*")
public class ActionPointController {
    @Autowired
    ActionPointService actionPointRepository;

    @PostMapping("/{identifier}")
    public ActionPoint createActionPoint(@PathVariable String identifier, @RequestBody ActionPoint actionPoint){
        return actionPointRepository.createActionPoint(actionPoint, identifier);
    }

}
