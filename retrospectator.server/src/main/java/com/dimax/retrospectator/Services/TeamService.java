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
import java.util.List;
import java.util.Set;


@Service
@Transactional
public class TeamService {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;



    public Team getTeamById(String identifier, User user) {
        Team team = teamRepository.findByIdentifier(identifier);
        checkForUserInTeam(user, team);

        return team;
    }


    public Team getTeamById(String identifier) {
        Team team = teamRepository.findByIdentifier(identifier);

        return team;
    }


    public Team saveTeam(Team team, User user) {
        Team createdTeam = teamRepository.save(team);

        Retro retro = new Retro(createdTeam);
        entityManager.persist(retro);

        List<Point> points = new ArrayList<>();

        createdTeam.setRetro(retro);
        createdTeam.getUser().add(user);
        retro.setPoint(points);

        String identifier = Base64Formater.uuidToBase64(createdTeam.getUid());
        createdTeam.setIdentifier(identifier);
        return createdTeam;
    }



    public Set<Team> getTeam(User user){
        return user.getTeam();
    }


    public Set<User> getUsersForCurrentTeam(String identifier){
        Team team = teamRepository.findByIdentifier(identifier);
        return team.getUser();
    }


    public Team updateTeamById(Team team, String id) {
        if(teamRepository.findByIdentifier(id) == null)
            return null;

        Team updatedTeam = teamRepository.getByIdentifier(id);

        updatedTeam.setTitle(team.getTitle());
        teamRepository.save(updatedTeam);

        return updatedTeam;
    }


    public Team deleteUserFromTeam(String identifier, User user){
        Team team = getTeamById(identifier);
        Set <User> users = team.getUser();
        users.remove(user);
        team.setUser(users);
        teamRepository.save(team);
        return team;
    }

    private void checkForUserInTeam(User user, Team team) {
        Set<User> users = team.getUser();
        boolean inTeam = false;

        for(User userInTeam : users){

            if(userInTeam.getId() == user.getId()) inTeam = true;
        }
        if (!inTeam){
            team.getUser().add(user);
            teamRepository.save(team);
        }
    }
}
