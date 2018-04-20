package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
