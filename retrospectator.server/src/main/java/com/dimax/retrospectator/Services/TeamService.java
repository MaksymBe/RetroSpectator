package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

    @Autowired
    TeamRepository repository;

    public Team getTeamById(Integer id){

        Team team = repository.findById(id).get();

        return team;
    }


    public Team saveTeam(Team team){
        Team createdTeam = repository.save(team);
        return createdTeam;
    };
}
