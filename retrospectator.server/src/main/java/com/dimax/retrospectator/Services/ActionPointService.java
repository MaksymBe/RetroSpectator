package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ActionPointService {

    @Autowired
    ActionPointRepository actionPointRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RetroRepository retroRepository;

    @PersistenceContext
    EntityManager entityManager;

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
    public List<ActionPoint> getActionPointsForRetro(int id, String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        List<Retro> retroes = team.getRetroes();
        for(Retro retro : retroes){
            if(retro.getId() == id){
                return retro.getActionPoint();
            }
        }

        Retro retro = retroRepository.getOne(id);
        return  null;
    }

    @Transactional
    public ActionPoint deleteActionPointById(int id) {
        ActionPoint actionPoint = actionPointRepository.getOne(id);
        actionPointRepository.deleteById(id);

        return actionPoint;
    }

    @Transactional
    public ActionPoint updateActionPointById(ActionPoint actionPoint, int id) {
        if(!actionPointRepository.existsById(id)) {
            return null;
        }

        actionPoint.setId(id);
        ActionPoint updatedActionPoint = entityManager.merge(actionPoint);

        return updatedActionPoint;
    }
}
