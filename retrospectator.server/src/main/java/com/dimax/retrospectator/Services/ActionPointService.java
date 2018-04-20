package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActionPointService {

    @Autowired
    ActionPointRepository repository;

    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public ActionPoint createActionPoint(ActionPoint actionPoint, String identifier){

        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = team.getRetro();
        actionPoint.setRetro(retro);

        ActionPoint createdActionPoint = repository.save(actionPoint);
        return createdActionPoint;

    }
}
