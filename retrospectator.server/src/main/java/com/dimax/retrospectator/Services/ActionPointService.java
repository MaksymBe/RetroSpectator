package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ActionPointService {

    @Autowired
    ActionPointRepository actionPointRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RetroRepository retroRepository;

    @Transactional
    public ActionPoint createActionPoint(ActionPoint actionPoint, String identifier){

        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = team.getRetro();
        actionPoint.setRetro(retro);

        ActionPoint createdActionPoint = actionPointRepository.save(actionPoint);
        return createdActionPoint;

    }

    @Transactional
    public List<ActionPoint> getActionPointsForTeam(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        return team.getRetro().getActionPoint();
    }

    @Transactional
    public List<ActionPoint> getActionPointsForRetro(int id){
        Retro retro = retroRepository.getOne(id);
        return  retro.getActionPoint();
    }

}
