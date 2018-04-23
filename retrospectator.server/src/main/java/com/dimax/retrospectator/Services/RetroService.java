package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class RetroService {

    @Autowired
    RetroRepository retroRepository;

    @Autowired
    TeamRepository teamRepository;

    @Transactional
    public Retro getCurrentRetro(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        return team.getRetro();
    }

    @Transactional
    public Retro getRetroById(int id){
        return  retroRepository.getOne(id);
    }

    @Transactional
    public void createNewRetro(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = new Retro(team);
//        entityManager.persist(retro);
        retroRepository.save(retro);
    }

    @Transactional
    public Retro startNewRetro(String identifier, String impression) {
        Retro lastRetro = getCurrentRetro(identifier);

        lastRetro.setFinishDate(new Date(System.currentTimeMillis()));
        lastRetro.setImpression(impression);
        retroRepository.save(lastRetro);

        Retro currentRetro = new Retro();
        currentRetro.setTeam(teamRepository.getByIdentifier(identifier));
        retroRepository.save(currentRetro);

        Team team = teamRepository.getByIdentifier(identifier);
        team.setRetro(currentRetro);
        teamRepository.save(team);
        return currentRetro;
    }
}
