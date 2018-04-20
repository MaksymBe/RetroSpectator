package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class PointService {

    @Autowired
    PointRepository repository;

    @Autowired
    RetroRepository retroRepository;

    @Autowired
    TeamRepository teamRepository;
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Point createPoint(Point point, User user, String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = team.getRetro();
        point.setUser(user);
        point.setRetro(retro);
        repository.save(point);
        //retro.getPoint().add(point);
//        entityManager.persist(retro);

        return point;

    }
}
