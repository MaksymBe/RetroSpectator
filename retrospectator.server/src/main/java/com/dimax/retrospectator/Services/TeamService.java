package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Base64Formater;
import com.dimax.retrospectator.Entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TeamService {

    @Autowired
    TeamRepository repository;

    public Team getTeamById(String link) {
        link = Base64Formater.uuidFromBase64(link);
        Team team = repository.findByLink(link);
        team.setLink(Base64Formater.uuidToBase64(team.getLink()));


        return team;
    }


    public Team saveTeam(Team team) {


        Team createdTeam = repository.save(team);
        System.out.println(createdTeam.getLink());
        createdTeam.setLink(Base64Formater.uuidToBase64(createdTeam.getLink()));
        System.out.println(Base64Formater.uuidFromBase64(createdTeam.getLink()));
        return createdTeam;
    };


}
