package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Service
@Transactional
public class RetroService {

    @Autowired
    RetroRepository retroRepository;

    @Autowired
    TeamRepository teamRepository;


    public Retro getCurrentRetro(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        return team.getRetro();
    }

    public Retro getRetroById(String identifier, int id){
        Team team = teamRepository.findByIdentifier(identifier);
        List<Retro> retroes = team.getRetroes();
        Retro retroFromHistory = retroes.stream().filter( retro ->  retro.getId()==id).findAny().orElse(null);

        return  retroFromHistory;
    }

    public void createNewRetro(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        Retro retro = new Retro(team);
        retroRepository.save(retro);
    }

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
