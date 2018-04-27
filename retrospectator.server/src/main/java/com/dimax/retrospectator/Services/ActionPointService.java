package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ActionPointService {

    @Autowired
    ActionPointRepository actionPointRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    RetroRepository retroRepository;

    @PersistenceContext
    EntityManager entityManager;


    public ActionPoint createActionPoint(ActionPoint actionPoint, String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = team.getRetro();
        actionPoint.setRetro(retro);
        actionPoint.setDate(new Date());

        ActionPoint createdActionPoint = actionPointRepository.save(actionPoint);
        return createdActionPoint;

    }

    public List<ActionPoint> getActionPointsForTeam(String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);
        return team.getRetro().getActionPoint();
    }

    public List<ActionPoint> getActionPointsForRetro(int id) {
        Retro retro = retroRepository.findById(id).get();
        List<ActionPoint> actionPoints = actionPointRepository.findAllByRetro(retro);

        return actionPoints;
    }

    public ActionPoint deleteActionPointById(int id) {
        ActionPoint actionPoint = actionPointRepository.findById(id).get();
        actionPointRepository.deleteById(id);

        return actionPoint;
    }

    public ActionPoint updateActionPointById(ActionPoint actionPoint, int id) {
        if (!actionPointRepository.existsById(id)) {
            return null;
        }

        ActionPoint pointToUpdate = actionPointRepository.findById(id).get();
        pointToUpdate.setTitle(actionPoint.getTitle());
        actionPointRepository.save(pointToUpdate);

        return pointToUpdate;
    }
}
