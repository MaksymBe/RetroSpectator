package com.dimax.retrospectator.Services;

import com.dimax.retrospectator.Base64Formater;
import com.dimax.retrospectator.Entity.Point;
import com.dimax.retrospectator.Entity.Retro;
import com.dimax.retrospectator.Entity.Team;
import com.dimax.retrospectator.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


@Service
public class TeamService {

    @Autowired
    TeamRepository repository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional
    public Team getTeamById(String identifier, User user) {
        Team team = repository.findByIdentifier(identifier);
        Set <User> users = team.getUser();
        boolean inTeam = false;
        for(User userInTeam : users){

            if(userInTeam.getId() == user.getId()) inTeam = true;
        }

        if (!inTeam){
            team.getUser().add(user);
            repository.save(team);
        }

        return team;
    }

    @Transactional
    public Team getTeamById(String identifier) {
        Team team = repository.findByIdentifier(identifier);

        return team;
    }

    @Transactional
    public Team saveTeam(Team team, User user) {
        Team createdTeam = repository.save(team);
        Retro retro = new Retro(createdTeam);
        List<Point> points = new ArrayList<>();
        entityManager.persist(retro);
        createdTeam.setRetro(retro);
        createdTeam.getUser().add(user);
        retro.setPoint(points);
        String identifier = Base64Formater.uuidToBase64(createdTeam.getUid());
        createdTeam.setIdentifier(identifier);
        return createdTeam;
    }


    @Transactional
    public Set<Team> getTeam(User user){
        return user.getTeam();
    }

    @Transactional
    public Set<User> getUsersForCurrentTeam(String identifier){
        Team team = repository.findByIdentifier(identifier);
        return team.getUser();
    }

    @Transactional
    public Team updateTeamById(Team team, String id) {
        if(repository.findByIdentifier(id) == null)
            return null;

        Team updatedTeam = repository.getByIdentifier(id);

        updatedTeam.setTitle(team.getTitle());
        repository.save(updatedTeam);

        return updatedTeam;
    }

    @Transactional
    public Team deleteUserFromTeam(String identifier, User user){
        Team team = getTeamById(identifier);
        Set <User> users = team.getUser();
        users.remove(user);
        team.setUser(users);
        repository.save(team);
        return team;
    }
}
