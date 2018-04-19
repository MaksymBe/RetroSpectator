package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Base64Formater;
import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Service
public class TeamService {

    @Autowired
    TeamRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public Team getTeamById(String identifier) {
        Team team = repository.findByIdentifier(identifier);
        return team;
    }

    @Transactional
    public Team saveTeam(Team team) {
        Team createdTeam = repository.save(team);
        Retro retro = new Retro(createdTeam);
        entityManager.persist(retro);
        createdTeam.setRetro(retro);

        String identifier = Base64Formater.uuidToBase64(createdTeam.getUid());
        createdTeam.setIdentifier(identifier);
        return createdTeam;
    }
    @Transactional
    public List<Team> getTeam(){
        return repository.findAll();
    }


}
