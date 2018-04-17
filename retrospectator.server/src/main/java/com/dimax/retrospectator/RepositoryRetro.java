package com.dimax.retrospectator;

import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepositoryRetro  {

    @Autowired
    TeamRepository repository;

    public Team[] get(){
        List<Team> all = repository.findAll();
        return all.toArray(new Team[]{});
    }


}
